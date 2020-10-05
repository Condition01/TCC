package br.com.ifeira.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class ComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

}

@RestController
class ServiceInstanceRestController {

	private static Logger logger = LoggerFactory.getLogger(ServiceInstanceRestController.class);

	@Value("${server.instance}")
	private String instance = "";
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@GetMapping("/instance")
	public ResponseEntity<?> instance(){
		try{
			return ResponseEntity.ok(instance);
		}catch (Exception ex){
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

}
