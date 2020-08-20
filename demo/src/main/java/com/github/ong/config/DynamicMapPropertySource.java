package com.github.ong.config;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

public class DynamicMapPropertySource extends EnumerablePropertySource<Map<String, Object>> {

    public DynamicMapPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }

    @Override
    public boolean containsProperty(String name) {
        return this.source.containsKey(name);
    }
}
