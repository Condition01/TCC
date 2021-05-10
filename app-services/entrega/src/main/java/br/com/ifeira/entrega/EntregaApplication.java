package br.com.ifeira.entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);
	}

}



