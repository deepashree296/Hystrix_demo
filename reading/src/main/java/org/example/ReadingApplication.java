package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;



@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix

public class ReadingApplication
{
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(ReadingApplication.class, args);
    }

@Bean
public RestTemplate rest(RestTemplateBuilder builder) {
    return builder.build();
   }

}
