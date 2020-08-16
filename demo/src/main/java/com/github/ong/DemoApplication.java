package com.github.ong;

import com.github.ong.rpc.HelloRpcService;
import com.github.ong.starter.config.auto.HttpConfiguration;
import com.github.ong.starter.config.properties.HttpConfigProperties;
import com.github.ong.starter.rpc.annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RpcScan(basePackage = "com.github.ong.rpc")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
    }
}
