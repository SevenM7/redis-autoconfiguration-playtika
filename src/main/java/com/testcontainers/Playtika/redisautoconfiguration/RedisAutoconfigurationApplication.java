package com.testcontainers.Playtika.redisautoconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisAutoconfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAutoconfigurationApplication.class, args);
	}

}
