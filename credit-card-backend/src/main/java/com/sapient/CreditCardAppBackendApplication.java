package com.sapient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.repository.CreditCardRepository;

@SpringBootApplication
public class CreditCardAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardAppBackendApplication.class, args);
		CreditCardRepository.init();
	}

}
