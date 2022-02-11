package com.kashitkalaecom.brandmodelmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kashitkalaecom.brandmodelmgmt.*")
public class BrandandmodelmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandandmodelmgmtApplication.class, args);
	}

}
