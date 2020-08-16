package com.github.ong.starter.config.properties;

import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rpc.http")
@Setter
public class HttpConfigProperties extends HttpConfigDefaultProperties{

    private String host;

    public String getHost() {
        return StringUtils.isBlank(host) ? HttpConfigDefaultProperties.DEFAULT_HOST : host;
    }
}
