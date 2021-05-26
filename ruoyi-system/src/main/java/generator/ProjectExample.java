package generator;

import java.util.ArrayList;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProjectExample() {
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

        public Criteria andProjidIsNull() {
            addCriterion("projId is null");
            return (Criteria) this;
        }

        public Criteria andProjidIsNotNull() {
            addCriterion("projId is not null");
            return (Criteria) this;
        }

        public Criteria andProjidEqualTo(Integer value) {
            addCriterion("projId =", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidNotEqualTo(Integer value) {
            addCriterion("projId <>", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidGreaterThan(Integer value) {
            addCriterion("projId >", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projId >=", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidLessThan(Integer value) {
            addCriterion("projId <", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidLessThanOrEqualTo(Integer value) {
            addCriterion("projId <=", value, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidIn(List<Integer> values) {
            addCriterion("projId in", values, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidNotIn(List<Integer> values) {
            addCriterion("projId not in", values, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidBetween(Integer value1, Integer value2) {
            addCriterion("projId between", value1, value2, "projid");
            return (Criteria) this;
        }

        public Criteria andProjidNotBetween(Integer value1, Integer value2) {
            addCriterion("projId not between", value1, value2, "projid");
            return (Criteria) this;
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

        public Criteria andLinecountIsNull() {
            addCriterion("LineCount is null");
            return (Criteria) this;
        }

        public Criteria andLinecountIsNotNull() {
            addCriterion("LineCount is not null");
            return (Criteria) this;
        }

        public Criteria andLinecountEqualTo(String value) {
            addCriterion("LineCount =", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountNotEqualTo(String value) {
            addCriterion("LineCount <>", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountGreaterThan(String value) {
            addCriterion("LineCount >", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountGreaterThanOrEqualTo(String value) {
            addCriterion("LineCount >=", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountLessThan(String value) {
            addCriterion("LineCount <", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountLessThanOrEqualTo(String value) {
            addCriterion("LineCount <=", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountLike(String value) {
            addCriterion("LineCount like", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountNotLike(String value) {
            addCriterion("LineCount not like", value, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountIn(List<String> values) {
            addCriterion("LineCount in", values, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountNotIn(List<String> values) {
            addCriterion("LineCount not in", values, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountBetween(String value1, String value2) {
            addCriterion("LineCount between", value1, value2, "linecount");
            return (Criteria) this;
        }

        public Criteria andLinecountNotBetween(String value1, String value2) {
            addCriterion("LineCount not between", value1, value2, "linecount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andManagerIsNull() {
            addCriterion("manager is null");
            return (Criteria) this;
        }

        public Criteria andManagerIsNotNull() {
            addCriterion("manager is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEqualTo(String value) {
            addCriterion("manager =", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotEqualTo(String value) {
            addCriterion("manager <>", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThan(String value) {
            addCriterion("manager >", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThanOrEqualTo(String value) {
            addCriterion("manager >=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThan(String value) {
            addCriterion("manager <", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThanOrEqualTo(String value) {
            addCriterion("manager <=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLike(String value) {
            addCriterion("manager like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotLike(String value) {
            addCriterion("manager not like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerIn(List<String> values) {
            addCriterion("manager in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotIn(List<String> values) {
            addCriterion("manager not in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerBetween(String value1, String value2) {
            addCriterion("manager between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotBetween(String value1, String value2) {
            addCriterion("manager not between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andMembercountIsNull() {
            addCriterion("membercount is null");
            return (Criteria) this;
        }

        public Criteria andMembercountIsNotNull() {
            addCriterion("membercount is not null");
            return (Criteria) this;
        }

        public Criteria andMembercountEqualTo(String value) {
            addCriterion("membercount =", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountNotEqualTo(String value) {
            addCriterion("membercount <>", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountGreaterThan(String value) {
            addCriterion("membercount >", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountGreaterThanOrEqualTo(String value) {
            addCriterion("membercount >=", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountLessThan(String value) {
            addCriterion("membercount <", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountLessThanOrEqualTo(String value) {
            addCriterion("membercount <=", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountLike(String value) {
            addCriterion("membercount like", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountNotLike(String value) {
            addCriterion("membercount not like", value, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountIn(List<String> values) {
            addCriterion("membercount in", values, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountNotIn(List<String> values) {
            addCriterion("membercount not in", values, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountBetween(String value1, String value2) {
            addCriterion("membercount between", value1, value2, "membercount");
            return (Criteria) this;
        }

        public Criteria andMembercountNotBetween(String value1, String value2) {
            addCriterion("membercount not between", value1, value2, "membercount");
            return (Criteria) this;
        }

        public Criteria andProjpathIsNull() {
            addCriterion("projPath is null");
            return (Criteria) this;
        }

        public Criteria andProjpathIsNotNull() {
            addCriterion("projPath is not null");
            return (Criteria) this;
        }

        public Criteria andProjpathEqualTo(String value) {
            addCriterion("projPath =", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathNotEqualTo(String value) {
            addCriterion("projPath <>", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathGreaterThan(String value) {
            addCriterion("projPath >", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathGreaterThanOrEqualTo(String value) {
            addCriterion("projPath >=", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathLessThan(String value) {
            addCriterion("projPath <", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathLessThanOrEqualTo(String value) {
            addCriterion("projPath <=", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathLike(String value) {
            addCriterion("projPath like", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathNotLike(String value) {
            addCriterion("projPath not like", value, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathIn(List<String> values) {
            addCriterion("projPath in", values, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathNotIn(List<String> values) {
            addCriterion("projPath not in", values, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathBetween(String value1, String value2) {
            addCriterion("projPath between", value1, value2, "projpath");
            return (Criteria) this;
        }

        public Criteria andProjpathNotBetween(String value1, String value2) {
            addCriterion("projPath not between", value1, value2, "projpath");
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