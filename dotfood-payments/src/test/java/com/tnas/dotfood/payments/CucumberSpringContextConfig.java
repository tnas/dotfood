package com.tnas.dotfood.payments;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) 
@ActiveProfiles("test")
public class CucumberSpringContextConfig {

	// This class is for running the Cucumber tests. 
}
