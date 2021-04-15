	package com.labproject.covid_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAnalyzerApplication.class, args);
	
	
	}

}
