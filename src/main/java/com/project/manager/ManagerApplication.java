package com.project.manager;

import com.project.manager.user.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

	@Override
	public void run(String... args) {
	}
}
