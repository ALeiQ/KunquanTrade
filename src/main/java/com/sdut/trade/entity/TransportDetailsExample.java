package com.sdut.trade.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TransportDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransportDetailsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIsNull() {
            addCriterion("load_time is null");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIsNotNull() {
            addCriterion("load_time is not null");
            return (Criteria) this;
        }

        public Criteria andLoadTimeEqualTo(Date value) {
            addCriterionForJDBCDate("load_time =", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("load_time <>", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("load_time >", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("load_time >=", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeLessThan(Date value) {
            addCriterionForJDBCDate("load_time <", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("load_time <=", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIn(List<Date> values) {
            addCriterionForJDBCDate("load_time in", values, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("load_time not in", values, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("load_time between", value1, value2, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("load_time not between", value1, value2, "loadTime");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIsNull() {
            addCriterion("goods_model is null");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIsNotNull() {
            addCriterion("goods_model is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsModelEqualTo(String value) {
            addCriterion("goods_model =", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelNotEqualTo(String value) {
            addCriterion("goods_model <>", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelGreaterThan(String value) {
            addCriterion("goods_model >", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelGreaterThanOrEqualTo(String value) {
            addCriterion("goods_model >=", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelLessThan(String value) {
            addCriterion("goods_model <", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelLessThanOrEqualTo(String value) {
            addCriterion("goods_model <=", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelLike(String value) {
            addCriterion("goods_model like", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelNotLike(String value) {
            addCriterion("goods_model not like", value, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIn(List<String> values) {
            addCriterion("goods_model in", values, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelNotIn(List<String> values) {
            addCriterion("goods_model not in", values, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelBetween(String value1, String value2) {
            addCriterion("goods_model between", value1, value2, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andGoodsModelNotBetween(String value1, String value2) {
            addCriterion("goods_model not between", value1, value2, "goodsModel");
            return (Criteria) this;
        }

        public Criteria andNetWeightIsNull() {
            addCriterion("net_weight is null");
            return (Criteria) this;
        }

        public Criteria andNetWeightIsNotNull() {
            addCriterion("net_weight is not null");
            return (Criteria) this;
        }

        public Criteria andNetWeightEqualTo(Float value) {
            addCriterion("net_weight =", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightNotEqualTo(Float value) {
            addCriterion("net_weight <>", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightGreaterThan(Float value) {
            addCriterion("net_weight >", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("net_weight >=", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightLessThan(Float value) {
            addCriterion("net_weight <", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightLessThanOrEqualTo(Float value) {
            addCriterion("net_weight <=", value, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightIn(List<Float> values) {
            addCriterion("net_weight in", values, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightNotIn(List<Float> values) {
            addCriterion("net_weight not in", values, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightBetween(Float value1, Float value2) {
            addCriterion("net_weight between", value1, value2, "netWeight");
            return (Criteria) this;
        }

        public Criteria andNetWeightNotBetween(Float value1, Float value2) {
            addCriterion("net_weight not between", value1, value2, "netWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightIsNull() {
            addCriterion("return_weight is null");
            return (Criteria) this;
        }

        public Criteria andReturnWeightIsNotNull() {
            addCriterion("return_weight is not null");
            return (Criteria) this;
        }

        public Criteria andReturnWeightEqualTo(Float value) {
            addCriterion("return_weight =", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightNotEqualTo(Float value) {
            addCriterion("return_weight <>", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightGreaterThan(Float value) {
            addCriterion("return_weight >", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("return_weight >=", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightLessThan(Float value) {
            addCriterion("return_weight <", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightLessThanOrEqualTo(Float value) {
            addCriterion("return_weight <=", value, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightIn(List<Float> values) {
            addCriterion("return_weight in", values, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightNotIn(List<Float> values) {
            addCriterion("return_weight not in", values, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightBetween(Float value1, Float value2) {
            addCriterion("return_weight between", value1, value2, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andReturnWeightNotBetween(Float value1, Float value2) {
            addCriterion("return_weight not between", value1, value2, "returnWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightIsNull() {
            addCriterion("loss_weight is null");
            return (Criteria) this;
        }

        public Criteria andLossWeightIsNotNull() {
            addCriterion("loss_weight is not null");
            return (Criteria) this;
        }

        public Criteria andLossWeightEqualTo(Float value) {
            addCriterion("loss_weight =", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightNotEqualTo(Float value) {
            addCriterion("loss_weight <>", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightGreaterThan(Float value) {
            addCriterion("loss_weight >", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("loss_weight >=", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightLessThan(Float value) {
            addCriterion("loss_weight <", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightLessThanOrEqualTo(Float value) {
            addCriterion("loss_weight <=", value, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightIn(List<Float> values) {
            addCriterion("loss_weight in", values, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightNotIn(List<Float> values) {
            addCriterion("loss_weight not in", values, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightBetween(Float value1, Float value2) {
            addCriterion("loss_weight between", value1, value2, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andLossWeightNotBetween(Float value1, Float value2) {
            addCriterion("loss_weight not between", value1, value2, "lossWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsFromIsNull() {
            addCriterion("goods_from is null");
            return (Criteria) this;
        }

        public Criteria andGoodsFromIsNotNull() {
            addCriterion("goods_from is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsFromEqualTo(String value) {
            addCriterion("goods_from =", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromNotEqualTo(String value) {
            addCriterion("goods_from <>", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromGreaterThan(String value) {
            addCriterion("goods_from >", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromGreaterThanOrEqualTo(String value) {
            addCriterion("goods_from >=", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromLessThan(String value) {
            addCriterion("goods_from <", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromLessThanOrEqualTo(String value) {
            addCriterion("goods_from <=", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromLike(String value) {
            addCriterion("goods_from like", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromNotLike(String value) {
            addCriterion("goods_from not like", value, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromIn(List<String> values) {
            addCriterion("goods_from in", values, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromNotIn(List<String> values) {
            addCriterion("goods_from not in", values, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromBetween(String value1, String value2) {
            addCriterion("goods_from between", value1, value2, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andGoodsFromNotBetween(String value1, String value2) {
            addCriterion("goods_from not between", value1, value2, "goodsFrom");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceIsNull() {
            addCriterion("seller_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceIsNotNull() {
            addCriterion("seller_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceEqualTo(Float value) {
            addCriterion("seller_unit_price =", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceNotEqualTo(Float value) {
            addCriterion("seller_unit_price <>", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceGreaterThan(Float value) {
            addCriterion("seller_unit_price >", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("seller_unit_price >=", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceLessThan(Float value) {
            addCriterion("seller_unit_price <", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceLessThanOrEqualTo(Float value) {
            addCriterion("seller_unit_price <=", value, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceIn(List<Float> values) {
            addCriterion("seller_unit_price in", values, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceNotIn(List<Float> values) {
            addCriterion("seller_unit_price not in", values, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceBetween(Float value1, Float value2) {
            addCriterion("seller_unit_price between", value1, value2, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerUnitPriceNotBetween(Float value1, Float value2) {
            addCriterion("seller_unit_price not between", value1, value2, "sellerUnitPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceIsNull() {
            addCriterion("seller_sum_price is null");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceIsNotNull() {
            addCriterion("seller_sum_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceEqualTo(Float value) {
            addCriterion("seller_sum_price =", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceNotEqualTo(Float value) {
            addCriterion("seller_sum_price <>", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceGreaterThan(Float value) {
            addCriterion("seller_sum_price >", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("seller_sum_price >=", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceLessThan(Float value) {
            addCriterion("seller_sum_price <", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceLessThanOrEqualTo(Float value) {
            addCriterion("seller_sum_price <=", value, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceIn(List<Float> values) {
            addCriterion("seller_sum_price in", values, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceNotIn(List<Float> values) {
            addCriterion("seller_sum_price not in", values, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceBetween(Float value1, Float value2) {
            addCriterion("seller_sum_price between", value1, value2, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andSellerSumPriceNotBetween(Float value1, Float value2) {
            addCriterion("seller_sum_price not between", value1, value2, "sellerSumPrice");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyIsNull() {
            addCriterion("buyer_company is null");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyIsNotNull() {
            addCriterion("buyer_company is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyEqualTo(String value) {
            addCriterion("buyer_company =", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyNotEqualTo(String value) {
            addCriterion("buyer_company <>", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyGreaterThan(String value) {
            addCriterion("buyer_company >", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_company >=", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyLessThan(String value) {
            addCriterion("buyer_company <", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyLessThanOrEqualTo(String value) {
            addCriterion("buyer_company <=", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyLike(String value) {
            addCriterion("buyer_company like", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyNotLike(String value) {
            addCriterion("buyer_company not like", value, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyIn(List<String> values) {
            addCriterion("buyer_company in", values, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyNotIn(List<String> values) {
            addCriterion("buyer_company not in", values, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyBetween(String value1, String value2) {
            addCriterion("buyer_company between", value1, value2, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andBuyerCompanyNotBetween(String value1, String value2) {
            addCriterion("buyer_company not between", value1, value2, "buyerCompany");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(Float value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(Float value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(Float value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(Float value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(Float value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<Float> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<Float> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(Float value1, Float value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(Float value1, Float value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNull() {
            addCriterion("sum_price is null");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNotNull() {
            addCriterion("sum_price is not null");
            return (Criteria) this;
        }

        public Criteria andSumPriceEqualTo(Float value) {
            addCriterion("sum_price =", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotEqualTo(Float value) {
            addCriterion("sum_price <>", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThan(Float value) {
            addCriterion("sum_price >", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("sum_price >=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThan(Float value) {
            addCriterion("sum_price <", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThanOrEqualTo(Float value) {
            addCriterion("sum_price <=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceIn(List<Float> values) {
            addCriterion("sum_price in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotIn(List<Float> values) {
            addCriterion("sum_price not in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceBetween(Float value1, Float value2) {
            addCriterion("sum_price between", value1, value2, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotBetween(Float value1, Float value2) {
            addCriterion("sum_price not between", value1, value2, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andTransCompanyIsNull() {
            addCriterion("trans_company is null");
            return (Criteria) this;
        }

        public Criteria andTransCompanyIsNotNull() {
            addCriterion("trans_company is not null");
            return (Criteria) this;
        }

        public Criteria andTransCompanyEqualTo(String value) {
            addCriterion("trans_company =", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyNotEqualTo(String value) {
            addCriterion("trans_company <>", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyGreaterThan(String value) {
            addCriterion("trans_company >", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("trans_company >=", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyLessThan(String value) {
            addCriterion("trans_company <", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyLessThanOrEqualTo(String value) {
            addCriterion("trans_company <=", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyLike(String value) {
            addCriterion("trans_company like", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyNotLike(String value) {
            addCriterion("trans_company not like", value, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyIn(List<String> values) {
            addCriterion("trans_company in", values, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyNotIn(List<String> values) {
            addCriterion("trans_company not in", values, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyBetween(String value1, String value2) {
            addCriterion("trans_company between", value1, value2, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransCompanyNotBetween(String value1, String value2) {
            addCriterion("trans_company not between", value1, value2, "transCompany");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceIsNull() {
            addCriterion("trans_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceIsNotNull() {
            addCriterion("trans_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceEqualTo(Float value) {
            addCriterion("trans_unit_price =", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceNotEqualTo(Float value) {
            addCriterion("trans_unit_price <>", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceGreaterThan(Float value) {
            addCriterion("trans_unit_price >", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("trans_unit_price >=", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceLessThan(Float value) {
            addCriterion("trans_unit_price <", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceLessThanOrEqualTo(Float value) {
            addCriterion("trans_unit_price <=", value, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceIn(List<Float> values) {
            addCriterion("trans_unit_price in", values, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceNotIn(List<Float> values) {
            addCriterion("trans_unit_price not in", values, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceBetween(Float value1, Float value2) {
            addCriterion("trans_unit_price between", value1, value2, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransUnitPriceNotBetween(Float value1, Float value2) {
            addCriterion("trans_unit_price not between", value1, value2, "transUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceIsNull() {
            addCriterion("trans_sum_price is null");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceIsNotNull() {
            addCriterion("trans_sum_price is not null");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceEqualTo(Float value) {
            addCriterion("trans_sum_price =", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceNotEqualTo(Float value) {
            addCriterion("trans_sum_price <>", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceGreaterThan(Float value) {
            addCriterion("trans_sum_price >", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("trans_sum_price >=", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceLessThan(Float value) {
            addCriterion("trans_sum_price <", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceLessThanOrEqualTo(Float value) {
            addCriterion("trans_sum_price <=", value, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceIn(List<Float> values) {
            addCriterion("trans_sum_price in", values, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceNotIn(List<Float> values) {
            addCriterion("trans_sum_price not in", values, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceBetween(Float value1, Float value2) {
            addCriterion("trans_sum_price between", value1, value2, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andTransSumPriceNotBetween(Float value1, Float value2) {
            addCriterion("trans_sum_price not between", value1, value2, "transSumPrice");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(Float value) {
            addCriterion("profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(Float value) {
            addCriterion("profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(Float value) {
            addCriterion("profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(Float value) {
            addCriterion("profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(Float value) {
            addCriterion("profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(Float value) {
            addCriterion("profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<Float> values) {
            addCriterion("profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<Float> values) {
            addCriterion("profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(Float value1, Float value2) {
            addCriterion("profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(Float value1, Float value2) {
            addCriterion("profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberIsNull() {
            addCriterion("weighing_number is null");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberIsNotNull() {
            addCriterion("weighing_number is not null");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberEqualTo(String value) {
            addCriterion("weighing_number =", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberNotEqualTo(String value) {
            addCriterion("weighing_number <>", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberGreaterThan(String value) {
            addCriterion("weighing_number >", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("weighing_number >=", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberLessThan(String value) {
            addCriterion("weighing_number <", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberLessThanOrEqualTo(String value) {
            addCriterion("weighing_number <=", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberLike(String value) {
            addCriterion("weighing_number like", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberNotLike(String value) {
            addCriterion("weighing_number not like", value, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberIn(List<String> values) {
            addCriterion("weighing_number in", values, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberNotIn(List<String> values) {
            addCriterion("weighing_number not in", values, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberBetween(String value1, String value2) {
            addCriterion("weighing_number between", value1, value2, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andWeighingNumberNotBetween(String value1, String value2) {
            addCriterion("weighing_number not between", value1, value2, "weighingNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNull() {
            addCriterion("car_number is null");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNotNull() {
            addCriterion("car_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumberEqualTo(String value) {
            addCriterion("car_number =", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotEqualTo(String value) {
            addCriterion("car_number <>", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThan(String value) {
            addCriterion("car_number >", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_number >=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThan(String value) {
            addCriterion("car_number <", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThanOrEqualTo(String value) {
            addCriterion("car_number <=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLike(String value) {
            addCriterion("car_number like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotLike(String value) {
            addCriterion("car_number not like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIn(List<String> values) {
            addCriterion("car_number in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotIn(List<String> values) {
            addCriterion("car_number not in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberBetween(String value1, String value2) {
            addCriterion("car_number between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotBetween(String value1, String value2) {
            addCriterion("car_number not between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIsNull() {
            addCriterion("delete_date is null");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIsNotNull() {
            addCriterion("delete_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteDateEqualTo(Date value) {
            addCriterion("delete_date =", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotEqualTo(Date value) {
            addCriterion("delete_date <>", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateGreaterThan(Date value) {
            addCriterion("delete_date >", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_date >=", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateLessThan(Date value) {
            addCriterion("delete_date <", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateLessThanOrEqualTo(Date value) {
            addCriterion("delete_date <=", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIn(List<Date> values) {
            addCriterion("delete_date in", values, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotIn(List<Date> values) {
            addCriterion("delete_date not in", values, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateBetween(Date value1, Date value2) {
            addCriterion("delete_date between", value1, value2, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotBetween(Date value1, Date value2) {
            addCriterion("delete_date not between", value1, value2, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Boolean value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Boolean value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Boolean value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Boolean value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Boolean> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Boolean> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}