package com.opay;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lizhi
 */
@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootOpayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOpayProviderApplication.class, args);
    }

}
