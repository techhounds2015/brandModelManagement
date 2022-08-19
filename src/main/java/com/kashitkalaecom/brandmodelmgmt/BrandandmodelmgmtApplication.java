package com.kashitkalaecom.brandmodelmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.kashitkalaecom.brandmodelmgmt.utilities.CommonUtility;

@SpringBootApplication
@ComponentScan("com.kashitkalaecom.brandmodelmgmt.*")
public class BrandandmodelmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandandmodelmgmtApplication.class, args);
		

		System.out.println(CommonUtility.distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
		System.out.println(CommonUtility.distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
		System.out.println(CommonUtility.distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
	}

}
