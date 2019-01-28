package com.springboot.xml.consumer.dubbo;

import com.springboot.xml.provider.dubbo.DubboProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDubboservice {
    @Autowired
    DubboProviderService dubboProviderService;

    public String getAppName(){
        String name=dubboProviderService.getAppName();
        System.out.print(name);
        return name;
    }
}
