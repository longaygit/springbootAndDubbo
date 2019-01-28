package com.springboot.apiconfig.consumer.controller;

import com.springboot.apiconfig.consumer.dubbo.ConsumerDubboserviceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumerApiConfig")
public class DubboConsumerController {
    @Autowired
    ConsumerDubboserviceConfig consumerDubboserviceConfig;

    @GetMapping("/getName")
    public String getAppName(){
        String name=consumerDubboserviceConfig.getAppName();
        System.out.print(name);
        return name;
    }
}
