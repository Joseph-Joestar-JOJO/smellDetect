package generator;

import java.util.ArrayList;
import java.util.List;

public class ProjmemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProjmemberExample() {
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

        public Criteria andMemberidIsNull() {
            addCriterion("memberID is null");
            return (Criteria) this;
        }

        public Criteria andMemberidIsNotNull() {
            addCriterion("memberID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberidEqualTo(String value) {
            addCriterion("memberID =", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotEqualTo(String value) {
            addCriterion("memberID <>", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThan(String value) {
            addCriterion("memberID >", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidGreaterThanOrEqualTo(String value) {
            addCriterion("memberID >=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThan(String value) {
            addCriterion("memberID <", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLessThanOrEqualTo(String value) {
            addCriterion("memberID <=", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidLike(String value) {
            addCriterion("memberID like", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotLike(String value) {
            addCriterion("memberID not like", value, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidIn(List<String> values) {
            addCriterion("memberID in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotIn(List<String> values) {
            addCriterion("memberID not in", values, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidBetween(String value1, String value2) {
            addCriterion("memberID between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMemberidNotBetween(String value1, String value2) {
            addCriterion("memberID not between", value1, value2, "memberid");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNull() {
            addCriterion("memberName is null");
            return (Criteria) this;
        }

        public Criteria andMembernameIsNotNull() {
            addCriterion("memberName is not null");
            return (Criteria) this;
        }

        public Criteria andMembernameEqualTo(String value) {
            addCriterion("memberName =", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotEqualTo(String value) {
            addCriterion("memberName <>", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThan(String value) {
            addCriterion("memberName >", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameGreaterThanOrEqualTo(String value) {
            addCriterion("memberName >=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThan(String value) {
            addCriterion("memberName <", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLessThanOrEqualTo(String value) {
            addCriterion("memberName <=", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameLike(String value) {
            addCriterion("memberName like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotLike(String value) {
            addCriterion("memberName not like", value, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameIn(List<String> values) {
            addCriterion("memberName in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotIn(List<String> values) {
            addCriterion("memberName not in", values, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameBetween(String value1, String value2) {
            addCriterion("memberName between", value1, value2, "membername");
            return (Criteria) this;
        }

        public Criteria andMembernameNotBetween(String value1, String value2) {
            addCriterion("memberName not between", value1, value2, "membername");
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