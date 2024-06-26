package com.mcode.llp.codeGen;

import com.mcode.llp.codeGen.models.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)

public class CodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGenApplication.class, args);
	}

}
