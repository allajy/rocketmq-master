package com.bxc.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bxc.store.api.HelloServiceApi;

/**
 * @author: Bixc
 * @date: 2024/07/15 09:21
 **/
@RestController
public class HelloController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.bxc.store.api.HelloServiceApi",
            check = false,
            timeout = 1000,
            retries = 0
    )
    private HelloServiceApi helloService;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        return helloService.sayHello(name);
    }

}
