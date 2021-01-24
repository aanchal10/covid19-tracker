package com.aanchal.coronavirusinvestigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.aanchal.coronavirusinvestigator.util.HitManager;

@SpringBootApplication
@EnableScheduling
public class CoronavirusInvestigatorApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CoronavirusInvestigatorApplication.class, args);
		HitManager.initializeHitValues();
	}

}
