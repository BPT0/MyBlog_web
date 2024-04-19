package dev.mvc.lecture_v2sbm3caws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class LectureV2sbm3awscApplication {

	public static void main(String[] args) {
		SpringApplication.run(LectureV2sbm3awscApplication.class, args);
	}

}