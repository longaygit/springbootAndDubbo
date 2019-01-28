package com.springboot.study.provider.dubbo.impl;

import com.springboot.study.provider.dataDO.ProviderInfo;
import com.springboot.study.provider.dubbo.DubboProviderService;
import com.alibaba.dubbo.config.annotation.Service;

//注册为dubbo服务
@Service
public class DubboProviderServiceImpl implements DubboProviderService {

    @Override
    public String getAppName(){
        ProviderInfo providerInfo=new ProviderInfo();
        providerInfo.setAppName("dubbo provider ");
        providerInfo.setVersion("1.0.0");

        return providerInfo.getAppName();
    }
}
