package com.example.DeepHole;

import com.example.DeepHole.models.User;
import com.example.DeepHole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeepHoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepHoleApplication.class, args);
	}


}
