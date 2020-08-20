package com.github.ong.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class DynamicEnvPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String key = "prop.test1";
        String property = environment.getProperty(key);
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put(key, "test1"+property);
        DynamicMapPropertySource dynamicMapPropertySource = new DynamicMapPropertySource("my1", sourceMap);
        environment.getPropertySources().addFirst(dynamicMapPropertySource);

    }
}
