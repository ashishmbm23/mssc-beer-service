package com.ashish.msscbeerservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "${application-title}",
				version = "${application-version}",
				description = "${application-description}",
				contact = @Contact(
						name = "Ashish",
						email = "ashishmbm23@gmail.com",
						url = "https://www.google.com"
				)
		)
)
@ComponentScan(basePackages = "com.ashish")
public class MSSCBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSSCBeerServiceApplication.class, args);
	}

}
