package com.github.ong;

import com.github.ong.config.DynamicEnvPostProcessor;
import com.github.ong.starter.rpc.annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RpcScan(basePackage = "com.github.ong.rpc")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        String property = applicationContext.getEnvironment().getProperty("prop.test1");
        System.out.println(property);
    }


}
