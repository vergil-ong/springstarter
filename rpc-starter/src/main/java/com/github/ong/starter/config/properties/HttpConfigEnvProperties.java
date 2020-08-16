package com.github.ong.starter.config.properties;

import com.github.ong.starter.rpc.constant.HttpProperty;
import lombok.Getter;
import org.springframework.core.env.Environment;

@Getter
public class HttpConfigEnvProperties extends HttpConfigProperties{

    private Environment environment;

    private String host;

    public HttpConfigEnvProperties(Environment environment) {
        this.environment = environment;
        setProperties();
    }

    public void setProperties(){
        this.host = environment.getProperty(HttpProperty.HOST.getPropertyCode());
    }


}
