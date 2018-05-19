package com.sdut.trade.entity;

import java.io.Serializable;
import java.util.Date;

public class LogisticsDetail implements Serializable {
    private Integer id;

    private Date loadTime;

    private String goodsName;

    private String goodsModel;

    private Double netWeight;

    private Double returnWeight;

    private Double lossWeight;

    private String goodsFrom;

    private Double sellerUnitPrice;

    private Double sellerSumPrice;

    private String buyerCompany;

    private Double unitPrice;

    private Double sumPrice;

    private String transCompany;

    private Double transUnitPrice;

    private Double transSumPrice;

    private Double profit;

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

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getReturnWeight() {
        return returnWeight;
    }

    public void setReturnWeight(Double returnWeight) {
        this.returnWeight = returnWeight;
    }

    public Double getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(Double lossWeight) {
        this.lossWeight = lossWeight;
    }

    public String getGoodsFrom() {
        return goodsFrom;
    }

    public void setGoodsFrom(String goodsFrom) {
        this.goodsFrom = goodsFrom == null ? null : goodsFrom.trim();
    }

    public Double getSellerUnitPrice() {
        return sellerUnitPrice;
    }

    public void setSellerUnitPrice(Double sellerUnitPrice) {
        this.sellerUnitPrice = sellerUnitPrice;
    }

    public Double getSellerSumPrice() {
        return sellerSumPrice;
    }

    public void setSellerSumPrice(Double sellerSumPrice) {
        this.sellerSumPrice = sellerSumPrice;
    }

    public String getBuyerCompany() {
        return buyerCompany;
    }

    public void setBuyerCompany(String buyerCompany) {
        this.buyerCompany = buyerCompany == null ? null : buyerCompany.trim();
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getTransCompany() {
        return transCompany;
    }

    public void setTransCompany(String transCompany) {
        this.transCompany = transCompany == null ? null : transCompany.trim();
    }

    public Double getTransUnitPrice() {
        return transUnitPrice;
    }

    public void setTransUnitPrice(Double transUnitPrice) {
        this.transUnitPrice = transUnitPrice;
    }

    public Double getTransSumPrice() {
        return transSumPrice;
    }

    public void setTransSumPrice(Double transSumPrice) {
        this.transSumPrice = transSumPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
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

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table logistics_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        loadTime("load_time"),
        goodsName("goods_name"),
        goodsModel("goods_model"),
        netWeight("net_weight"),
        returnWeight("return_weight"),
        lossWeight("loss_weight"),
        goodsFrom("goods_from"),
        sellerUnitPrice("seller_unit_price"),
        sellerSumPrice("seller_sum_price"),
        buyerCompany("buyer_company"),
        unitPrice("unit_price"),
        sumPrice("sum_price"),
        transCompany("trans_company"),
        transUnitPrice("trans_unit_price"),
        transSumPrice("trans_sum_price"),
        profit("profit"),
        weighingNumber("weighing_number"),
        carNumber("car_number"),
        remark("remark"),
        createDate("create_date"),
        deleteDate("delete_date"),
        enable("enable");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table logistics_detail
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}