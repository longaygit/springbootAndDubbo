package com.springboot.apiconfig.consumer.dubbo;

import com.alibaba.dubbo.config.*;
import com.springboot.apiconfig.provider.dubbo.DubboProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDubboserviceConfig {

    //API 仅用于 OpenAPI, ESB, Test, Mock 等系统集成，普通服务提供方或消费方，请采用XML 配置方式使用 Dubbo ↩︎
//实现一：
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumer-apiConfig");
        return applicationConfig;
    }
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }
    @Bean
    public ProtocolConfig setProtocol(){
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20883);
        return protocol;
    }

    @Bean
    public ReferenceConfig<DubboProviderService> referenceConfig(){
        ReferenceConfig<DubboProviderService> reference = new ReferenceConfig<DubboProviderService>();
        reference.setApplication(applicationConfig());
        reference.setRegistry(registryConfig()); // 多个注册中心可以用setRegistries()
       // reference.setProtocol(setProtocol()); // 多个协议可以用setProtocols()
        reference.setInterface(DubboProviderService.class);
       // reference.setVersion("1.0.0");

        return reference;
    }

    public String getAppName(){
        DubboProviderService dubboProviderService=referenceConfig().get();
        String name=dubboProviderService.getAppName();
        System.out.print(name);
        return name;
    }
}
