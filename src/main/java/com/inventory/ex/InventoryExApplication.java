package com.inventory.ex;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * This is the Main class to start the Product endpoint.
 */
@SpringBootApplication
public class InventoryExApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryExApplication.class, args);
	}

	// Configure the Time Zone to UTC to avoid Time Zone adjustments in the application.
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
