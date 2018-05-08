package com.sdut.trade.entity;

import java.io.Serializable;
import java.util.Date;

public class DealDetails implements Serializable {
    private Integer id;

    private Date date;

    private Integer amount;

    private Boolean type;

    private Integer way;

    private String wechatPayAccount;

    private String wechatReceiveAccount;

    private Long bankPayAccount;

    private Long bankReceiveAccount;

    private String bankName;

    private Long checkNumber;

    private Integer checkAmount;

    private String checkPeoplePay;

    private String checkPeopleReceive;

    private Date checkDate;

    private Date checkDeadline;

    private String remark;

    private Date createDate;

    private Date deleteDate;

    private Boolean enable;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getWechatPayAccount() {
        return wechatPayAccount;
    }

    public void setWechatPayAccount(String wechatPayAccount) {
        this.wechatPayAccount = wechatPayAccount == null ? null : wechatPayAccount.trim();
    }

    public String getWechatReceiveAccount() {
        return wechatReceiveAccount;
    }

    public void setWechatReceiveAccount(String wechatReceiveAccount) {
        this.wechatReceiveAccount = wechatReceiveAccount == null ? null : wechatReceiveAccount.trim();
    }

    public Long getBankPayAccount() {
        return bankPayAccount;
    }

    public void setBankPayAccount(Long bankPayAccount) {
        this.bankPayAccount = bankPayAccount;
    }

    public Long getBankReceiveAccount() {
        return bankReceiveAccount;
    }

    public void setBankReceiveAccount(Long bankReceiveAccount) {
        this.bankReceiveAccount = bankReceiveAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Long getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Long checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Integer getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Integer checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getCheckPeoplePay() {
        return checkPeoplePay;
    }

    public void setCheckPeoplePay(String checkPeoplePay) {
        this.checkPeoplePay = checkPeoplePay == null ? null : checkPeoplePay.trim();
    }

    public String getCheckPeopleReceive() {
        return checkPeopleReceive;
    }

    public void setCheckPeopleReceive(String checkPeopleReceive) {
        this.checkPeopleReceive = checkPeopleReceive == null ? null : checkPeopleReceive.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDeadline() {
        return checkDeadline;
    }

    public void setCheckDeadline(Date checkDeadline) {
        this.checkDeadline = checkDeadline;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}