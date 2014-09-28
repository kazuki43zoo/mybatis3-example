package com.example.domain.model;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/09/28.
 */
public class AccountAddress implements Serializable {

    private String accountUuid;
    private String zipCode;
    private String address;

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
