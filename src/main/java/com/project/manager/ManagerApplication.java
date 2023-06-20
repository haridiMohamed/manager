package com.project.manager;

import com.project.manager.user.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;
@Slf4j
@SpringBootApplication
public class ManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String absoluteDiskPath =  System.getProperty("user.home")+File.separator;
		String relativeWebPath = Constants.relativePath;
		File file1 = new File(absoluteDiskPath,relativeWebPath);
		file1.mkdirs();
		if (!file1.exists()) {
			if (file1.mkdir()) {
				log.debug("Directory is created!");
			} else {
				log.debug("ailed to create directory!");
			}
		}
	}
}
