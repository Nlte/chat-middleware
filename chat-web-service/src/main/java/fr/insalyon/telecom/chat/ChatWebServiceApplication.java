package fr.insalyon.telecom.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class ChatWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatWebServiceApplication.class, args);
	}
}
