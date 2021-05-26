package generator;

import java.util.ArrayList;
import java.util.List;

public class SmellcountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public SmellcountExample() {
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

        public Criteria andProjurlIsNull() {
            addCriterion("projURL is null");
            return (Criteria) this;
        }

        public Criteria andProjurlIsNotNull() {
            addCriterion("projURL is not null");
            return (Criteria) this;
        }

        public Criteria andProjurlEqualTo(String value) {
            addCriterion("projURL =", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotEqualTo(String value) {
            addCriterion("projURL <>", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlGreaterThan(String value) {
            addCriterion("projURL >", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlGreaterThanOrEqualTo(String value) {
            addCriterion("projURL >=", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlLessThan(String value) {
            addCriterion("projURL <", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlLessThanOrEqualTo(String value) {
            addCriterion("projURL <=", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlLike(String value) {
            addCriterion("projURL like", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotLike(String value) {
            addCriterion("projURL not like", value, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlIn(List<String> values) {
            addCriterion("projURL in", values, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotIn(List<String> values) {
            addCriterion("projURL not in", values, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlBetween(String value1, String value2) {
            addCriterion("projURL between", value1, value2, "projurl");
            return (Criteria) this;
        }

        public Criteria andProjurlNotBetween(String value1, String value2) {
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

        public Criteria andBlobcountIsNull() {
            addCriterion("BlobCount is null");
            return (Criteria) this;
        }

        public Criteria andBlobcountIsNotNull() {
            addCriterion("BlobCount is not null");
            return (Criteria) this;
        }

        public Criteria andBlobcountEqualTo(Integer value) {
            addCriterion("BlobCount =", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountNotEqualTo(Integer value) {
            addCriterion("BlobCount <>", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountGreaterThan(Integer value) {
            addCriterion("BlobCount >", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("BlobCount >=", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountLessThan(Integer value) {
            addCriterion("BlobCount <", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountLessThanOrEqualTo(Integer value) {
            addCriterion("BlobCount <=", value, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountIn(List<Integer> values) {
            addCriterion("BlobCount in", values, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountNotIn(List<Integer> values) {
            addCriterion("BlobCount not in", values, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountBetween(Integer value1, Integer value2) {
            addCriterion("BlobCount between", value1, value2, "blobcount");
            return (Criteria) this;
        }

        public Criteria andBlobcountNotBetween(Integer value1, Integer value2) {
            addCriterion("BlobCount not between", value1, value2, "blobcount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountIsNull() {
            addCriterion("FeatureEnvyCount is null");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountIsNotNull() {
            addCriterion("FeatureEnvyCount is not null");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountEqualTo(Integer value) {
            addCriterion("FeatureEnvyCount =", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountNotEqualTo(Integer value) {
            addCriterion("FeatureEnvyCount <>", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountGreaterThan(Integer value) {
            addCriterion("FeatureEnvyCount >", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountGreaterThanOrEqualTo(Integer value) {
            addCriterion("FeatureEnvyCount >=", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountLessThan(Integer value) {
            addCriterion("FeatureEnvyCount <", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountLessThanOrEqualTo(Integer value) {
            addCriterion("FeatureEnvyCount <=", value, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountIn(List<Integer> values) {
            addCriterion("FeatureEnvyCount in", values, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountNotIn(List<Integer> values) {
            addCriterion("FeatureEnvyCount not in", values, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountBetween(Integer value1, Integer value2) {
            addCriterion("FeatureEnvyCount between", value1, value2, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andFeatureenvycountNotBetween(Integer value1, Integer value2) {
            addCriterion("FeatureEnvyCount not between", value1, value2, "featureenvycount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountIsNull() {
            addCriterion("LongMethodCount is null");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountIsNotNull() {
            addCriterion("LongMethodCount is not null");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountEqualTo(Integer value) {
            addCriterion("LongMethodCount =", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountNotEqualTo(Integer value) {
            addCriterion("LongMethodCount <>", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountGreaterThan(Integer value) {
            addCriterion("LongMethodCount >", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LongMethodCount >=", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountLessThan(Integer value) {
            addCriterion("LongMethodCount <", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountLessThanOrEqualTo(Integer value) {
            addCriterion("LongMethodCount <=", value, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountIn(List<Integer> values) {
            addCriterion("LongMethodCount in", values, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountNotIn(List<Integer> values) {
            addCriterion("LongMethodCount not in", values, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountBetween(Integer value1, Integer value2) {
            addCriterion("LongMethodCount between", value1, value2, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andLongmethodcountNotBetween(Integer value1, Integer value2) {
            addCriterion("LongMethodCount not between", value1, value2, "longmethodcount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountIsNull() {
            addCriterion("MisplacedClassCount is null");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountIsNotNull() {
            addCriterion("MisplacedClassCount is not null");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountEqualTo(Integer value) {
            addCriterion("MisplacedClassCount =", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountNotEqualTo(Integer value) {
            addCriterion("MisplacedClassCount <>", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountGreaterThan(Integer value) {
            addCriterion("MisplacedClassCount >", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("MisplacedClassCount >=", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountLessThan(Integer value) {
            addCriterion("MisplacedClassCount <", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountLessThanOrEqualTo(Integer value) {
            addCriterion("MisplacedClassCount <=", value, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountIn(List<Integer> values) {
            addCriterion("MisplacedClassCount in", values, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountNotIn(List<Integer> values) {
            addCriterion("MisplacedClassCount not in", values, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountBetween(Integer value1, Integer value2) {
            addCriterion("MisplacedClassCount between", value1, value2, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andMisplacedclasscountNotBetween(Integer value1, Integer value2) {
            addCriterion("MisplacedClassCount not between", value1, value2, "misplacedclasscount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountIsNull() {
            addCriterion("PromiscuousPackageCount is null");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountIsNotNull() {
            addCriterion("PromiscuousPackageCount is not null");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountEqualTo(Integer value) {
            addCriterion("PromiscuousPackageCount =", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountNotEqualTo(Integer value) {
            addCriterion("PromiscuousPackageCount <>", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountGreaterThan(Integer value) {
            addCriterion("PromiscuousPackageCount >", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("PromiscuousPackageCount >=", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountLessThan(Integer value) {
            addCriterion("PromiscuousPackageCount <", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountLessThanOrEqualTo(Integer value) {
            addCriterion("PromiscuousPackageCount <=", value, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountIn(List<Integer> values) {
            addCriterion("PromiscuousPackageCount in", values, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountNotIn(List<Integer> values) {
            addCriterion("PromiscuousPackageCount not in", values, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountBetween(Integer value1, Integer value2) {
            addCriterion("PromiscuousPackageCount between", value1, value2, "promiscuouspackagecount");
            return (Criteria) this;
        }

        public Criteria andPromiscuouspackagecountNotBetween(Integer value1, Integer value2) {
            addCriterion("PromiscuousPackageCount not between", value1, value2, "promiscuouspackagecount");
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