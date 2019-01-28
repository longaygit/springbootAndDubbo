package com.springboot.study.consumer.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.study.provider.dubbo.DubboProviderService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDubboservice {
    @Reference
    DubboProviderService dubboProviderService;

    public String getAppName(){
        String name=dubboProviderService.getAppName();
        System.out.print(name);
        return name;
    }
}
