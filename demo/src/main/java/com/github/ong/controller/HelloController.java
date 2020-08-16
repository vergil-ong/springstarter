package com.github.ong.controller;

import com.github.ong.rpc.HelloRpcService;
import com.github.ong.starter.HttpRpcClient;
import com.github.ong.starter.config.properties.HttpConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HttpRpcClient httpRpcClient;


    @Resource
    private HelloRpcService helloRpcService;

    @RequestMapping("/test1")
    public String test1(String arg1){
        log.info("arg1 {}",arg1);
        return "hello world " + arg1;
    }

    @RequestMapping("/test2")
    public String test2(String arg1){
        log.info("arg1 {}",arg1);
        return "hello world " + arg1 +" "+ httpRpcClient.getHost();
    }

    @RequestMapping("/test3")
    public String test3(String wd){
        log.info("wd {}",wd);
        String result = helloRpcService.test1(wd);
        return result;
    }
}
