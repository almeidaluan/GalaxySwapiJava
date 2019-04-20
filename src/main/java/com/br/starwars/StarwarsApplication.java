package com.br.starwars;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
public class StarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propsConfig = new PropertySourcesPlaceholderConfigurer();
		propsConfig.setLocation(new ClassPathResource("git.properties"));
		propsConfig.setIgnoreResourceNotFound(true);
		propsConfig.setIgnoreUnresolvablePlaceholders(true);
		return propsConfig;
	}
}
