package com.opay;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lizhi
 */
@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerApplication.class, args);
    }

}
