package com.springboot.apiconfig.provider.dubbo.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.apiconfig.provider.dataDO.ProviderInfo;
import com.springboot.apiconfig.provider.dubbo.DubboProviderService;

@Service
public class DubboProviderServiceImpl implements DubboProviderService {
    @Override
    public String getAppName(){
        ProviderInfo providerInfo=new ProviderInfo();
        providerInfo.setAppName("dubbo apiConfig provider ");
        providerInfo.setVersion("1.0.0");

        return providerInfo.getAppName();
    }
}
