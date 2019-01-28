package com.springboot.xml.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// 使用 dubbo.xml 配置
@ImportResource(value = {"classpath:dubbo.xml"})

public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}

/**
 * http://localhost:8083/consumerXml/getName
 */
//dubbo.xml属性提取修改的文件
//@PropertySource("classpath:dubbo/dubbo.properties")