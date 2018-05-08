package com.sdut.trade.entity;

import java.io.Serializable;
import java.util.Date;

public class TransportDetails implements Serializable {
    private Integer id;

    private Date loadTime;

    private String goodsName;

    private String goodsModel;

    private Float netWeight;

    private Float returnWeight;

    private Float lossWeight;

    private String goodsFrom;

    private Float sellerUnitPrice;

    private Float sellerSumPrice;

    private String buyerCompany;

    private Float unitPrice;

    private Float sumPrice;

    private String transCompany;

    private Float transUnitPrice;

    private Float transSumPrice;

    private Float profit;

    private String weighingNumber;

    private String carNumber;

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

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel == null ? null : goodsModel.trim();
    }

    public Float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Float netWeight) {
        this.netWeight = netWeight;
    }

    public Float getReturnWeight() {
        return returnWeight;
    }

    public void setReturnWeight(Float returnWeight) {
        this.returnWeight = returnWeight;
    }

    public Float getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(Float lossWeight) {
        this.lossWeight = lossWeight;
    }

    public String getGoodsFrom() {
        return goodsFrom;
    }

    public void setGoodsFrom(String goodsFrom) {
        this.goodsFrom = goodsFrom == null ? null : goodsFrom.trim();
    }

    public Float getSellerUnitPrice() {
        return sellerUnitPrice;
    }

    public void setSellerUnitPrice(Float sellerUnitPrice) {
        this.sellerUnitPrice = sellerUnitPrice;
    }

    public Float getSellerSumPrice() {
        return sellerSumPrice;
    }

    public void setSellerSumPrice(Float sellerSumPrice) {
        this.sellerSumPrice = sellerSumPrice;
    }

    public String getBuyerCompany() {
        return buyerCompany;
    }

    public void setBuyerCompany(String buyerCompany) {
        this.buyerCompany = buyerCompany == null ? null : buyerCompany.trim();
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getTransCompany() {
        return transCompany;
    }

    public void setTransCompany(String transCompany) {
        this.transCompany = transCompany == null ? null : transCompany.trim();
    }

    public Float getTransUnitPrice() {
        return transUnitPrice;
    }

    public void setTransUnitPrice(Float transUnitPrice) {
        this.transUnitPrice = transUnitPrice;
    }

    public Float getTransSumPrice() {
        return transSumPrice;
    }

    public void setTransSumPrice(Float transSumPrice) {
        this.transSumPrice = transSumPrice;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public String getWeighingNumber() {
        return weighingNumber;
    }

    public void setWeighingNumber(String weighingNumber) {
        this.weighingNumber = weighingNumber == null ? null : weighingNumber.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
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