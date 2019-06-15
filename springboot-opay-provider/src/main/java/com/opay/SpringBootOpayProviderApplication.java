package com.opay;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lizhi
 */
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = {"com.opay.dao"})
public class SpringBootOpayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOpayProviderApplication.class, args);
    }

}
