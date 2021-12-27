package com.bl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Registering All Services
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class BookstoreEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreEurekaServerApplication.class, args);
	}

}
