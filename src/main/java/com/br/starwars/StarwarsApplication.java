package com.br.starwars;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
public class StarwarsApplication {


	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);



	}


}
