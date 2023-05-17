package com.api.moedaestudantil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MoedaEstudantilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoedaEstudantilApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Sistema moeda estudantil";
	}
}
