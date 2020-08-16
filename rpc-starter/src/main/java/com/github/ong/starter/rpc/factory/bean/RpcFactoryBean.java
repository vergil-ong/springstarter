package com.github.ong.starter.rpc.factory.bean;

import com.github.ong.starter.config.properties.HttpConfigProperties;
import com.github.ong.starter.rpc.proxy.RpcInvocationHandler;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class RpcFactoryBean<T> implements FactoryBean {

    private Class<T> targetClass;

    private HttpConfigProperties httpConfigProperties;

    public RpcFactoryBean(Class<T> targetClass, HttpConfigProperties httpConfigProperties) {
        this.targetClass = targetClass;
        this.httpConfigProperties = httpConfigProperties;
    }

    @Override
    public Object getObject() throws Exception {
        RpcInvocationHandler rpcInvocationHandler = new RpcInvocationHandler(targetClass, httpConfigProperties);
        return Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass}, rpcInvocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return targetClass;
    }
}
