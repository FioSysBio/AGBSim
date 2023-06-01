package br.fiocruz.procc.acbmservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jep.MainInterpreter;
import jep.PyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@OpenAPIDefinition(info = @Info(title = "AGBSim API", version = "1.0", description = "API for agent-based simulation system."))
@SpringBootApplication
public class AcbmServiceApplication {

	public static void main(String[] args) {

		//SETUP DO PATH DAS VARIÁVEIS REFERENTES AO AMBIENTE PYTHON (NO CASO AQUI USANDO ANACONDA)
//		File pythonHome = new File("/softwares/anaconda3");
//		PyConfig conf = new PyConfig();
//		conf.setPythonHome(pythonHome.getAbsolutePath());
//		MainInterpreter.setInitParams(conf);

		SpringApplication.run(AcbmServiceApplication.class, args);
	}

}
