
package com.jony.platform.model.req;


public class ServiceReq {
    private String serviceId;//参数名称
    private String org;//	机构号	否
    private String idNo;//身份证号
    private String BusinessFlag;//业务标识

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getBusinessFlag() {
        return BusinessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        BusinessFlag = businessFlag;
    }
}
