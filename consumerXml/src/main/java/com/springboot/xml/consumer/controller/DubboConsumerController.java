package com.springboot.xml.consumer.controller;

import com.springboot.xml.consumer.dubbo.ConsumerDubboservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumerXml")
public class DubboConsumerController {
    @Autowired
    ConsumerDubboservice consumerDubboservice;

    @GetMapping("/getName")
    public String getAppName(){
        String name=consumerDubboservice.getAppName();
        System.out.print(name);
        return name;
    }
}
