package com.github.ong.starter.rpc.constant;

import lombok.Getter;

@Getter
public enum HttpProperty {

    HOST(1, "rpc.http.host")
    ;

    private Integer code;

    private String propertyCode;

    HttpProperty(Integer code, String propertyCode) {
        this.code = code;
        this.propertyCode = propertyCode;
    }
}
