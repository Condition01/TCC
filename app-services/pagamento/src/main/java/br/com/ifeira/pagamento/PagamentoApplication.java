package br.com.ifeira.pagamento;

import br.com.ifeira.pagamento.config.APIConfig;
import br.com.ifeira.pagamento.config.QueueConfig;
import br.com.ifeira.pagamento.factories.PagamentoOutConcretHandlerFactory;
import br.com.ifeira.pagamento.factories.PagamentoOutHandlerFactory;
import br.com.ifeira.pagamento.handlers.PagamentoOutHandler;
import br.com.ifeira.pagamento.shared.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/teste")
public class PagamentoApplication {


	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private QueueConfig config;

	@Autowired
	private APIConfig apiConfig;

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApplication.class, args);
	}

	@GetMapping("/teste")
	ResponseEntity<?> teste() throws Exception {
		PublicKey publicKey = readPublicKey("keys/public.der");

		PagamentoOutHandlerFactory pagFactory = new PagamentoOutConcretHandlerFactory();

		PagamentoOutHandler pagHandlers = pagFactory.criarPagamentoOutChain(this.apiConfig);

		//cryptos
		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setNumeroCartao(new String(encrypt(publicKey,"12312313".getBytes()), StandardCharsets.ISO_8859_1));
		pagamentoDTO.setCvv(new String(encrypt(publicKey,"123".getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
		pagamentoDTO.setValidadeCartao(new String(encrypt(publicKey,"20/03".getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));

		//endereco
		pagamentoDTO.setRua("Rua das bermudas");
		pagamentoDTO.setNumeroCasa("46");
		pagamentoDTO.setCidade("Cerqueira César");
		pagamentoDTO.setComplemento("Atrás do campo");
		pagamentoDTO.setBairro("São Lucas");
		pagamentoDTO.setUF("SP");
		pagamentoDTO.setCodigoPostal("18767030");

		//email
		pagamentoDTO.setEmail("brunofc11@gmail.com");

		//billings
		pagamentoDTO.setIdCobranca("chr_D5BCA3E1EC2966DFA44907391C2690F2");
		pagamentoDTO.setCredId("2e16c846-5bfb-4f69-842a-399fc31c099c");

//		this.rabbitTemplate.convertAndSend(this.config.TOPIC_EXCHANGE_NAME, this.config.KEY_NAME, a);
		return ResponseEntity.ok(pagHandlers.handle(pagamentoDTO));
	}

	public static byte[] readFileBytes(String filename) throws IOException {
		Path path = Paths.get(filename);
		return Files.readAllBytes(path);
	}

	public static PublicKey readPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(filename));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(publicSpec);
	}

	public static PrivateKey readPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(filename));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	public static byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(plaintext);
	}

	public static byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(ciphertext);
	}

}



