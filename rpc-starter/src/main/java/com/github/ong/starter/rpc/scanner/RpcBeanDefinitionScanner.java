package com.github.ong.starter.rpc.scanner;

import com.github.ong.starter.config.properties.HttpConfigEnvProperties;
import com.github.ong.starter.config.properties.HttpConfigProperties;
import com.github.ong.starter.rpc.factory.bean.RpcFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class RpcBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private HttpConfigProperties httpConfigProperties;

    public RpcBeanDefinitionScanner(BeanDefinitionRegistry registry, HttpConfigProperties httpConfigProperties) {
        super(registry);
        this.httpConfigProperties = httpConfigProperties;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean candidateComponent = super.isCandidateComponent(beanDefinition);
        if (!candidateComponent){
            return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
        }
        return candidateComponent;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolderSet = super.doScan(basePackages);

        if (CollectionUtils.isEmpty(beanDefinitionHolderSet)){
            log.debug("beanDefinitionHolders is empty");
        }

        processBeanDifinitions(beanDefinitionHolderSet, httpConfigProperties);

        return beanDefinitionHolderSet;
    }

    private void processBeanDifinitions(Set<BeanDefinitionHolder> beanDefinitionHolderSet, HttpConfigProperties httpConfigProperties){
        if (CollectionUtils.isEmpty(beanDefinitionHolderSet)){
            return;
        }
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolderSet){
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
            constructorArgumentValues.addGenericArgumentValue(beanDefinition.getBeanClassName());
            constructorArgumentValues.addGenericArgumentValue(httpConfigProperties);
            beanDefinition.setBeanClass(RpcFactoryBean.class);
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
        }
    }

}
