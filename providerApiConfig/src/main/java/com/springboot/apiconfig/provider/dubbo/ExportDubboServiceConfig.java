package com.springboot.apiconfig.provider.dubbo;
import com.alibaba.dubbo.config.*;
import com.springboot.apiconfig.provider.dubbo.impl.DubboProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
public class ExportDubboServiceConfig {

    //API 仅用于 OpenAPI, ESB, Test, Mock 等系统集成，普通服务提供方或消费方，请采用XML 配置方式使用 Dubbo ↩︎
//实现一：
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider-apiConfig");
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
    public ServiceConfig<DubboProviderService> userServiceConfig(DubboProviderService dubboProviderService){
        ServiceConfig<DubboProviderService> service = new ServiceConfig<DubboProviderService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(applicationConfig());
        service.setRegistry(registryConfig()); // 多个注册中心可以用setRegistries()
        service.setProtocol(setProtocol()); // 多个协议可以用setProtocols()
        service.setInterface(DubboProviderService.class);
        service.setRef(dubboProviderService);
        service.setVersion("1.0.0");

        //配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getAppName");
        methodConfig.setTimeout(1000);

        //将method的设置关联到service配置中
        List<MethodConfig> methods = new ArrayList<>();
        methods.add(methodConfig);
        service.setMethods(methods);
        return service;
    }

    //实现二：
    public static void exportService(){
        // 服务实现
        DubboProviderService dubboProviderService = new DubboProviderServiceImpl();

        // 当前应用配置
        //<dubbo:application name="provider-apiConfig"></dubbo:application>
        ApplicationConfig application = new ApplicationConfig();
        application.setName("provider-apiConfig");

        // 连接注册中心配置
        //<dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("127.0.0.1:2181");
//       registry.setUsername("aaa");
//       registry.setPassword("bbb");

        // 服务提供者协议配置
        // <dubbo:protocol name="dubbo" port="20883" />
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20883);
        // protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置
//       <dubbo:service interface="com.springboot.xml.provider.dubbo.DubboProviderService"
//                   ref="dubboProviderService" timeout="10000" >
//       <dubbo:method name="getAppName" timeout="1000"></dubbo:method>
//       </dubbo:service>
        ServiceConfig<DubboProviderService> service = new ServiceConfig<DubboProviderService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(DubboProviderService.class);
        service.setRef(dubboProviderService);
        service.setVersion("1.0.0");

        //配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getAppName");
        methodConfig.setTimeout(1000);

        //将method的设置关联到service配置中
        List<MethodConfig> methods = new ArrayList<>();
        methods.add(methodConfig);
        service.setMethods(methods);

// 暴露及注册服务
        service.export();
    }
    private static AtomicBoolean running = new AtomicBoolean(false);
    public static void main(String[] args) {
        while (true) {
            if (!running.get()) {
                exportService();
                running.set(true);
            }
        }
    }
}
