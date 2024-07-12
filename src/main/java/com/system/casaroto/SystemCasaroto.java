package com.system.casaroto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemCasaroto {

	public static void main(String[] args) {
		SpringApplication.run(SystemCasaroto.class, args);

		System.out.println("Servidor rodando");
	}
}
