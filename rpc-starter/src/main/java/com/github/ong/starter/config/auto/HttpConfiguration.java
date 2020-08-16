package com.github.ong.starter.config.auto;

import com.github.ong.starter.HttpRpcClient;
import com.github.ong.starter.config.properties.HttpConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableConfigurationProperties(HttpConfigProperties.class)
public class HttpConfiguration {

    @Resource
    private HttpConfigProperties httpConfigProperties;

    @Bean
    @ConditionalOnMissingBean(HttpRpcClient.class)
    public HttpRpcClient httpRpcClient(){
        HttpRpcClient httpRpcClient = new HttpRpcClient();

        httpRpcClient.setHost(httpConfigProperties.getHost());

        return httpRpcClient;
    }
}
