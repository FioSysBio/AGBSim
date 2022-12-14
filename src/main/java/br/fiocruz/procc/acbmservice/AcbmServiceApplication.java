package br.fiocruz.procc.acbmservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "AGBSim API", version = "1.0", description = "API for agent-based simulation system."))
@SpringBootApplication
public class AcbmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcbmServiceApplication.class, args);
	}

}
