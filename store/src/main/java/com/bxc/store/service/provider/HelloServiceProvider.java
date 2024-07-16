package com.bxc.store.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.bxc.store.api.HelloServiceApi;

/**
 * @author: Bixc
 * @date: 2024/07/15 09:36
 **/
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceProvider implements HelloServiceApi {
    @Override
    public String sayHello(String name) {
        System.out.println("name:"+name);
        return "hello "+name;
    }
}
