package com.personal.batch.hatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WebSocketPocApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebSocketPocApplication.class, args);
    }
}
