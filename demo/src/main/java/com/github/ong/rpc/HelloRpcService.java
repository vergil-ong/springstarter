package com.github.ong.rpc;

import com.github.ong.starter.rpc.annotation.RpcScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public interface HelloRpcService {

    @GetMapping("")
    public String test1(String wd);
}
