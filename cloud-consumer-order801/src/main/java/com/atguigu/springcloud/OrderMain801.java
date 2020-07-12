package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jdx
 * @date 2020-06-17 09:41
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderMain801 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain801.class,args);
    }
}
