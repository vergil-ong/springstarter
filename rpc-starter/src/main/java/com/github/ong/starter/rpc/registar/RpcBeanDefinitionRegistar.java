package com.github.ong.starter.rpc.registar;

import com.github.ong.starter.config.properties.HttpConfigEnvProperties;
import com.github.ong.starter.config.properties.HttpConfigProperties;
import com.github.ong.starter.rpc.annotation.RpcScan;
import com.github.ong.starter.rpc.scanner.RpcBeanDefinitionScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class RpcBeanDefinitionRegistar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private HttpConfigEnvProperties httpConfigEnvProperties;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(RpcScan.class.getName()));

        RpcBeanDefinitionScanner rpcBeanDefinitionScanner = new RpcBeanDefinitionScanner(beanDefinitionRegistry, httpConfigEnvProperties);
        rpcBeanDefinitionScanner.scan(annotationAttributes.getString("basePackage"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        HttpConfigEnvProperties httpConfigEnvProperties = new HttpConfigEnvProperties(environment);
        this.httpConfigEnvProperties = httpConfigEnvProperties;
    }
}
