package com.bikesandwheels.config;

/**
 * Информация о биометрическом процессе
 */
public class BioProcessInfo {
    
    private String serviceName;
    private String processId;
    private String basePath;

    public BioProcessInfo() {
    }

    public BioProcessInfo(String serviceName, String processId, String basePath) {
        this.serviceName = serviceName;
        this.processId = processId;
        this.basePath = basePath;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String toString() {
        return "BioProcessInfo{" +
                "serviceName='" + serviceName + '\'' +
                ", processId='" + processId + '\'' +
                ", basePath='" + basePath + '\'' +
                '}';
    }
}