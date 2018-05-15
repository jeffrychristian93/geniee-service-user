package com.pji.genieeserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GenieeServiceUserApplication {

    public static void main(String[] args){
        SpringApplication.run(GenieeServiceUserApplication.class, args);
    }
}
