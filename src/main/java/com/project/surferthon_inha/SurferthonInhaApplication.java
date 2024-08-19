package com.project.surferthon_inha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SurferthonInhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurferthonInhaApplication.class, args);
	}

}
