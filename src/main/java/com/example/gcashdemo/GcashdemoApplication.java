package com.example.gcashdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.gcashdemo.deliverycost.DeliveryCostController;


@SpringBootApplication
public class GcashdemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(GcashdemoApplication.class, args);
		//SpringApplication.run(RestServiceController.class, args);
		SpringApplication.run(DeliveryCostController.class, args);
		
	}

}
