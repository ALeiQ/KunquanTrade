package com.sdut.trade.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DealDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DealDetailsExample() {
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

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Boolean value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Boolean value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Boolean value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Boolean value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Boolean> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Boolean> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andWayIsNull() {
            addCriterion("way is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("way is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(Integer value) {
            addCriterion("way =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(Integer value) {
            addCriterion("way <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(Integer value) {
            addCriterion("way >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("way >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(Integer value) {
            addCriterion("way <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(Integer value) {
            addCriterion("way <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<Integer> values) {
            addCriterion("way in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<Integer> values) {
            addCriterion("way not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(Integer value1, Integer value2) {
            addCriterion("way between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(Integer value1, Integer value2) {
            addCriterion("way not between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountIsNull() {
            addCriterion("wechat_pay_account is null");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountIsNotNull() {
            addCriterion("wechat_pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountEqualTo(String value) {
            addCriterion("wechat_pay_account =", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountNotEqualTo(String value) {
            addCriterion("wechat_pay_account <>", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountGreaterThan(String value) {
            addCriterion("wechat_pay_account >", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_pay_account >=", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountLessThan(String value) {
            addCriterion("wechat_pay_account <", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountLessThanOrEqualTo(String value) {
            addCriterion("wechat_pay_account <=", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountLike(String value) {
            addCriterion("wechat_pay_account like", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountNotLike(String value) {
            addCriterion("wechat_pay_account not like", value, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountIn(List<String> values) {
            addCriterion("wechat_pay_account in", values, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountNotIn(List<String> values) {
            addCriterion("wechat_pay_account not in", values, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountBetween(String value1, String value2) {
            addCriterion("wechat_pay_account between", value1, value2, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatPayAccountNotBetween(String value1, String value2) {
            addCriterion("wechat_pay_account not between", value1, value2, "wechatPayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountIsNull() {
            addCriterion("wechat_receive_account is null");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountIsNotNull() {
            addCriterion("wechat_receive_account is not null");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountEqualTo(String value) {
            addCriterion("wechat_receive_account =", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountNotEqualTo(String value) {
            addCriterion("wechat_receive_account <>", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountGreaterThan(String value) {
            addCriterion("wechat_receive_account >", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_receive_account >=", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountLessThan(String value) {
            addCriterion("wechat_receive_account <", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountLessThanOrEqualTo(String value) {
            addCriterion("wechat_receive_account <=", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountLike(String value) {
            addCriterion("wechat_receive_account like", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountNotLike(String value) {
            addCriterion("wechat_receive_account not like", value, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountIn(List<String> values) {
            addCriterion("wechat_receive_account in", values, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountNotIn(List<String> values) {
            addCriterion("wechat_receive_account not in", values, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountBetween(String value1, String value2) {
            addCriterion("wechat_receive_account between", value1, value2, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andWechatReceiveAccountNotBetween(String value1, String value2) {
            addCriterion("wechat_receive_account not between", value1, value2, "wechatReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountIsNull() {
            addCriterion("bank_pay_account is null");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountIsNotNull() {
            addCriterion("bank_pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountEqualTo(Long value) {
            addCriterion("bank_pay_account =", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountNotEqualTo(Long value) {
            addCriterion("bank_pay_account <>", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountGreaterThan(Long value) {
            addCriterion("bank_pay_account >", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountGreaterThanOrEqualTo(Long value) {
            addCriterion("bank_pay_account >=", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountLessThan(Long value) {
            addCriterion("bank_pay_account <", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountLessThanOrEqualTo(Long value) {
            addCriterion("bank_pay_account <=", value, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountIn(List<Long> values) {
            addCriterion("bank_pay_account in", values, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountNotIn(List<Long> values) {
            addCriterion("bank_pay_account not in", values, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountBetween(Long value1, Long value2) {
            addCriterion("bank_pay_account between", value1, value2, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankPayAccountNotBetween(Long value1, Long value2) {
            addCriterion("bank_pay_account not between", value1, value2, "bankPayAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountIsNull() {
            addCriterion("bank_receive_account is null");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountIsNotNull() {
            addCriterion("bank_receive_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountEqualTo(Long value) {
            addCriterion("bank_receive_account =", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountNotEqualTo(Long value) {
            addCriterion("bank_receive_account <>", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountGreaterThan(Long value) {
            addCriterion("bank_receive_account >", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountGreaterThanOrEqualTo(Long value) {
            addCriterion("bank_receive_account >=", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountLessThan(Long value) {
            addCriterion("bank_receive_account <", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountLessThanOrEqualTo(Long value) {
            addCriterion("bank_receive_account <=", value, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountIn(List<Long> values) {
            addCriterion("bank_receive_account in", values, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountNotIn(List<Long> values) {
            addCriterion("bank_receive_account not in", values, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountBetween(Long value1, Long value2) {
            addCriterion("bank_receive_account between", value1, value2, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankReceiveAccountNotBetween(Long value1, Long value2) {
            addCriterion("bank_receive_account not between", value1, value2, "bankReceiveAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIsNull() {
            addCriterion("check_number is null");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIsNotNull() {
            addCriterion("check_number is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNumberEqualTo(Long value) {
            addCriterion("check_number =", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotEqualTo(Long value) {
            addCriterion("check_number <>", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberGreaterThan(Long value) {
            addCriterion("check_number >", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("check_number >=", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberLessThan(Long value) {
            addCriterion("check_number <", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberLessThanOrEqualTo(Long value) {
            addCriterion("check_number <=", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIn(List<Long> values) {
            addCriterion("check_number in", values, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotIn(List<Long> values) {
            addCriterion("check_number not in", values, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberBetween(Long value1, Long value2) {
            addCriterion("check_number between", value1, value2, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotBetween(Long value1, Long value2) {
            addCriterion("check_number not between", value1, value2, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckAmountIsNull() {
            addCriterion("check_amount is null");
            return (Criteria) this;
        }

        public Criteria andCheckAmountIsNotNull() {
            addCriterion("check_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCheckAmountEqualTo(Integer value) {
            addCriterion("check_amount =", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountNotEqualTo(Integer value) {
            addCriterion("check_amount <>", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountGreaterThan(Integer value) {
            addCriterion("check_amount >", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_amount >=", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountLessThan(Integer value) {
            addCriterion("check_amount <", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountLessThanOrEqualTo(Integer value) {
            addCriterion("check_amount <=", value, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountIn(List<Integer> values) {
            addCriterion("check_amount in", values, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountNotIn(List<Integer> values) {
            addCriterion("check_amount not in", values, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountBetween(Integer value1, Integer value2) {
            addCriterion("check_amount between", value1, value2, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("check_amount not between", value1, value2, "checkAmount");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayIsNull() {
            addCriterion("check_people_pay is null");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayIsNotNull() {
            addCriterion("check_people_pay is not null");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayEqualTo(String value) {
            addCriterion("check_people_pay =", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayNotEqualTo(String value) {
            addCriterion("check_people_pay <>", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayGreaterThan(String value) {
            addCriterion("check_people_pay >", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayGreaterThanOrEqualTo(String value) {
            addCriterion("check_people_pay >=", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayLessThan(String value) {
            addCriterion("check_people_pay <", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayLessThanOrEqualTo(String value) {
            addCriterion("check_people_pay <=", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayLike(String value) {
            addCriterion("check_people_pay like", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayNotLike(String value) {
            addCriterion("check_people_pay not like", value, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayIn(List<String> values) {
            addCriterion("check_people_pay in", values, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayNotIn(List<String> values) {
            addCriterion("check_people_pay not in", values, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayBetween(String value1, String value2) {
            addCriterion("check_people_pay between", value1, value2, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeoplePayNotBetween(String value1, String value2) {
            addCriterion("check_people_pay not between", value1, value2, "checkPeoplePay");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveIsNull() {
            addCriterion("check_people_receive is null");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveIsNotNull() {
            addCriterion("check_people_receive is not null");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveEqualTo(String value) {
            addCriterion("check_people_receive =", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveNotEqualTo(String value) {
            addCriterion("check_people_receive <>", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveGreaterThan(String value) {
            addCriterion("check_people_receive >", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveGreaterThanOrEqualTo(String value) {
            addCriterion("check_people_receive >=", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveLessThan(String value) {
            addCriterion("check_people_receive <", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveLessThanOrEqualTo(String value) {
            addCriterion("check_people_receive <=", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveLike(String value) {
            addCriterion("check_people_receive like", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveNotLike(String value) {
            addCriterion("check_people_receive not like", value, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveIn(List<String> values) {
            addCriterion("check_people_receive in", values, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveNotIn(List<String> values) {
            addCriterion("check_people_receive not in", values, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveBetween(String value1, String value2) {
            addCriterion("check_people_receive between", value1, value2, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckPeopleReceiveNotBetween(String value1, String value2) {
            addCriterion("check_people_receive not between", value1, value2, "checkPeopleReceive");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("check_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("check_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Date value) {
            addCriterionForJDBCDate("check_date =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Date value) {
            addCriterionForJDBCDate("check_date >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Date value) {
            addCriterionForJDBCDate("check_date <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Date> values) {
            addCriterionForJDBCDate("check_date in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("check_date not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineIsNull() {
            addCriterion("check_deadline is null");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineIsNotNull() {
            addCriterion("check_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineEqualTo(Date value) {
            addCriterionForJDBCDate("check_deadline =", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineNotEqualTo(Date value) {
            addCriterionForJDBCDate("check_deadline <>", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineGreaterThan(Date value) {
            addCriterionForJDBCDate("check_deadline >", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_deadline >=", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineLessThan(Date value) {
            addCriterionForJDBCDate("check_deadline <", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_deadline <=", value, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineIn(List<Date> values) {
            addCriterionForJDBCDate("check_deadline in", values, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineNotIn(List<Date> values) {
            addCriterionForJDBCDate("check_deadline not in", values, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_deadline between", value1, value2, "checkDeadline");
            return (Criteria) this;
        }

        public Criteria andCheckDeadlineNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_deadline not between", value1, value2, "checkDeadline");
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