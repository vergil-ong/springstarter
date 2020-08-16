package com.github.ong.starter.rpc.proxy;

import com.github.ong.starter.config.properties.HttpConfigProperties;
import com.github.ong.starter.rpc.annotation.RpcScan;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RpcInvocationHandler implements InvocationHandler {

    private RestTemplate restTemplate = new RestTemplate();

    private Class targetClass;

    private HttpConfigProperties httpConfigProperties;

    public RpcInvocationHandler(Class targetClass, HttpConfigProperties httpConfigProperties) {
        this.targetClass = targetClass;
        this.httpConfigProperties = httpConfigProperties;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String host = httpConfigProperties.getHost();
        String url = getUrl(method);

        Class<?> returnType = method.getReturnType();

        String param = getParam(method, args);

        Object resultObj = restTemplate.getForObject(host + url + param, returnType);

        return resultObj;
    }


    private String getUrl(Method method){
        GetMapping getMapping = method.getAnnotation(GetMapping.class);

        if (Objects.isNull(getMapping)){
            return HttpConfigProperties.DEFAULT_URL;
        }
        String[] urls = getMapping.value();
        List<String> urlList = Arrays.asList(urls);
        if (CollectionUtils.isEmpty(urlList)){
            return HttpConfigProperties.DEFAULT_URL;
        }
        return urlList.get(0);
    }

    private String getParam(Method method, Object[] args){
        Parameter[] parameters = method.getParameters();
        if (Objects.isNull(parameters)){
            return HttpConfigProperties.DEFAULT_PARAM;
        }

        StringBuilder paramBuilder = new StringBuilder("?");

        for (int i = 0; i < parameters.length; i++) {
            if ( i != 0){
                paramBuilder.append("&");
            }
            Parameter parameter = parameters[i];
            String requestParam = getRequestParam(parameter);
            paramBuilder.append(requestParam)
                    .append("=")
                    .append(args[i]);
        }
        return paramBuilder.toString();
    }

    private String getRequestParam(Parameter parameter){
        RequestParam requestParamAnnotation = parameter.getAnnotation(RequestParam.class);
        if (Objects.isNull(requestParamAnnotation)){
            return parameter.getName();
        }
        return requestParamAnnotation.value();
    }
}
