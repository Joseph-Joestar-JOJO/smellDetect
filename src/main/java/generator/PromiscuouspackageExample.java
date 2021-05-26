package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromiscuouspackageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public PromiscuouspackageExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andProjurlIsNull() {
            addCriterion("projURL is null");
            return (Criteria) this;
        }

        public Criteria andProjurlIsNotNull() {
            addCriterion("projURL is not null");
            return (Criteria) this;
        }

        public Criteria andProjurlEqualTo(Integer value) {
            addCriterion("projURL =", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotEqualTo(Integer value) {
            addCriterion("projURL <>", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlGreaterThan(Integer value) {
            addCriterion("projURL >", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlGreaterThanOrEqualTo(Integer value) {
            addCriterion("projURL >=", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlLessThan(Integer value) {
            addCriterion("projURL <", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlLessThanOrEqualTo(Integer value) {
            addCriterion("projURL <=", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlIn(List<Integer> values) {
            addCriterion("projURL in", values, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotIn(List<Integer> values) {
            addCriterion("projURL not in", values, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlBetween(Integer value1, Integer value2) {
            addCriterion("projURL between", value1, value2, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotBetween(Integer value1, Integer value2) {
            addCriterion("projURL not between", value1, value2, "projurl");
            return (Criteria) this;
        }

        public Criteria andCommithashIsNull() {
            addCriterion("commitHash is null");
            return (Criteria) this;
        }

        public Criteria andCommithashIsNotNull() {
            addCriterion("commitHash is not null");
            return (Criteria) this;
        }

        public Criteria andCommithashEqualTo(String value) {
            addCriterion("commitHash =", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashNotEqualTo(String value) {
            addCriterion("commitHash <>", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashGreaterThan(String value) {
            addCriterion("commitHash >", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashGreaterThanOrEqualTo(String value) {
            addCriterion("commitHash >=", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashLessThan(String value) {
            addCriterion("commitHash <", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashLessThanOrEqualTo(String value) {
            addCriterion("commitHash <=", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashLike(String value) {
            addCriterion("commitHash like", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashNotLike(String value) {
            addCriterion("commitHash not like", value, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashIn(List<String> values) {
            addCriterion("commitHash in", values, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashNotIn(List<String> values) {
            addCriterion("commitHash not in", values, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashBetween(String value1, String value2) {
            addCriterion("commitHash between", value1, value2, "commithash");
            return (Criteria) this;
        }

        public Criteria andCommithashNotBetween(String value1, String value2) {
            addCriterion("commitHash not between", value1, value2, "commithash");
            return (Criteria) this;
        }

        public Criteria andPackagenameIsNull() {
            addCriterion("PackageName is null");
            return (Criteria) this;
        }

        public Criteria andPackagenameIsNotNull() {
            addCriterion("PackageName is not null");
            return (Criteria) this;
        }

        public Criteria andPackagenameEqualTo(String value) {
            addCriterion("PackageName =", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameNotEqualTo(String value) {
            addCriterion("PackageName <>", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameGreaterThan(String value) {
            addCriterion("PackageName >", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameGreaterThanOrEqualTo(String value) {
            addCriterion("PackageName >=", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameLessThan(String value) {
            addCriterion("PackageName <", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameLessThanOrEqualTo(String value) {
            addCriterion("PackageName <=", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameLike(String value) {
            addCriterion("PackageName like", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameNotLike(String value) {
            addCriterion("PackageName not like", value, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameIn(List<String> values) {
            addCriterion("PackageName in", values, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameNotIn(List<String> values) {
            addCriterion("PackageName not in", values, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameBetween(String value1, String value2) {
            addCriterion("PackageName between", value1, value2, "packagename");
            return (Criteria) this;
        }

        public Criteria andPackagenameNotBetween(String value1, String value2) {
            addCriterion("PackageName not between", value1, value2, "packagename");
            return (Criteria) this;
        }

        public Criteria andOccurtimeIsNull() {
            addCriterion("occurTime is null");
            return (Criteria) this;
        }

        public Criteria andOccurtimeIsNotNull() {
            addCriterion("occurTime is not null");
            return (Criteria) this;
        }

        public Criteria andOccurtimeEqualTo(Date value) {
            addCriterionForJDBCDate("occurTime =", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("occurTime <>", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("occurTime >", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("occurTime >=", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeLessThan(Date value) {
            addCriterionForJDBCDate("occurTime <", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("occurTime <=", value, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeIn(List<Date> values) {
            addCriterionForJDBCDate("occurTime in", values, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("occurTime not in", values, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("occurTime between", value1, value2, "occurtime");
            return (Criteria) this;
        }

        public Criteria andOccurtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("occurTime not between", value1, value2, "occurtime");
            return (Criteria) this;
        }

        public Criteria andProjnameIsNull() {
            addCriterion("projName is null");
            return (Criteria) this;
        }

        public Criteria andProjnameIsNotNull() {
            addCriterion("projName is not null");
            return (Criteria) this;
        }

        public Criteria andProjnameEqualTo(String value) {
            addCriterion("projName =", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameNotEqualTo(String value) {
            addCriterion("projName <>", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameGreaterThan(String value) {
            addCriterion("projName >", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameGreaterThanOrEqualTo(String value) {
            addCriterion("projName >=", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameLessThan(String value) {
            addCriterion("projName <", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameLessThanOrEqualTo(String value) {
            addCriterion("projName <=", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameLike(String value) {
            addCriterion("projName like", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameNotLike(String value) {
            addCriterion("projName not like", value, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameIn(List<String> values) {
            addCriterion("projName in", values, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameNotIn(List<String> values) {
            addCriterion("projName not in", values, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameBetween(String value1, String value2) {
            addCriterion("projName between", value1, value2, "projname");
            return (Criteria) this;
        }

        public Criteria andProjnameNotBetween(String value1, String value2) {
            addCriterion("projName not between", value1, value2, "projname");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeIsNull() {
            addCriterion("eliminateTime is null");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeIsNotNull() {
            addCriterion("eliminateTime is not null");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeEqualTo(String value) {
            addCriterion("eliminateTime =", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeNotEqualTo(String value) {
            addCriterion("eliminateTime <>", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeGreaterThan(String value) {
            addCriterion("eliminateTime >", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("eliminateTime >=", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeLessThan(String value) {
            addCriterion("eliminateTime <", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeLessThanOrEqualTo(String value) {
            addCriterion("eliminateTime <=", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeLike(String value) {
            addCriterion("eliminateTime like", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeNotLike(String value) {
            addCriterion("eliminateTime not like", value, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeIn(List<String> values) {
            addCriterion("eliminateTime in", values, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeNotIn(List<String> values) {
            addCriterion("eliminateTime not in", values, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeBetween(String value1, String value2) {
            addCriterion("eliminateTime between", value1, value2, "eliminatetime");
            return (Criteria) this;
        }

        public Criteria andEliminatetimeNotBetween(String value1, String value2) {
            addCriterion("eliminateTime not between", value1, value2, "eliminatetime");
            return (Criteria) this;
        }
    }

    /**
     */
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