package com.rhandycana.AutomotricesRepuestos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomotricesRepuestosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AutomotricesRepuestosApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*-*-*-Camila esta funcionando-*-*-*");
    }
}
