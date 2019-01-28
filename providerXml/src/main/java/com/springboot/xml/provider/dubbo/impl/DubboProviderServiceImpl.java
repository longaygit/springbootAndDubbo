package com.springboot.xml.provider.dubbo.impl;


import com.springboot.xml.provider.dataDO.ProviderInfo;
import com.springboot.xml.provider.dubbo.DubboProviderService;

public class DubboProviderServiceImpl implements DubboProviderService {
    @Override
    public String getAppName(){
        ProviderInfo providerInfo=new ProviderInfo();
        providerInfo.setAppName("dubbo XML-Config provider ");
        providerInfo.setVersion("1.0.0");

        return providerInfo.getAppName();
    }
}
