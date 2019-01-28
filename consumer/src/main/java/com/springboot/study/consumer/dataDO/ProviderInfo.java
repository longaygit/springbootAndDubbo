package com.springboot.study.consumer.dataDO;

public class ProviderInfo {
    private String appName;
    private String version;
    private String serviceName;

    public String getAppName() {
        return appName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
