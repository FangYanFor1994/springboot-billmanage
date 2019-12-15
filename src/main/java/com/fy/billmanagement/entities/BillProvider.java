package com.fy.billmanagement.entities;

/**
 * @program: billmanagement-springboot->BillProvider
 * @description: bill+providerName属性的实体类
 * @author: fangyan
 * @create: 2019-12-11 23:58
 **/
public class BillProvider extends Bill{

    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

}
