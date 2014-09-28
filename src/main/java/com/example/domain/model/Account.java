package com.example.domain.model;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/09/28.
 */
public class Account implements Serializable {

    private String accountUuid;
    private String accountName;
    private DateTime birthDate;
    private AccountAddress address;

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public AccountAddress getAddress() {
        return address;
    }

    public void setAddress(AccountAddress address) {
        this.address = address;
    }
}
