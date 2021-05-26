package generator;

import java.util.ArrayList;
import java.util.List;

public class ProjcommitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProjcommitExample() {
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

        public Criteria andCommitauthorIsNull() {
            addCriterion("commitAuthor is null");
            return (Criteria) this;
        }

        public Criteria andCommitauthorIsNotNull() {
            addCriterion("commitAuthor is not null");
            return (Criteria) this;
        }

        public Criteria andCommitauthorEqualTo(String value) {
            addCriterion("commitAuthor =", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorNotEqualTo(String value) {
            addCriterion("commitAuthor <>", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorGreaterThan(String value) {
            addCriterion("commitAuthor >", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorGreaterThanOrEqualTo(String value) {
            addCriterion("commitAuthor >=", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorLessThan(String value) {
            addCriterion("commitAuthor <", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorLessThanOrEqualTo(String value) {
            addCriterion("commitAuthor <=", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorLike(String value) {
            addCriterion("commitAuthor like", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorNotLike(String value) {
            addCriterion("commitAuthor not like", value, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorIn(List<String> values) {
            addCriterion("commitAuthor in", values, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorNotIn(List<String> values) {
            addCriterion("commitAuthor not in", values, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorBetween(String value1, String value2) {
            addCriterion("commitAuthor between", value1, value2, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitauthorNotBetween(String value1, String value2) {
            addCriterion("commitAuthor not between", value1, value2, "commitauthor");
            return (Criteria) this;
        }

        public Criteria andCommitdateIsNull() {
            addCriterion("commitDate is null");
            return (Criteria) this;
        }

        public Criteria andCommitdateIsNotNull() {
            addCriterion("commitDate is not null");
            return (Criteria) this;
        }

        public Criteria andCommitdateEqualTo(String value) {
            addCriterion("commitDate =", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateNotEqualTo(String value) {
            addCriterion("commitDate <>", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateGreaterThan(String value) {
            addCriterion("commitDate >", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateGreaterThanOrEqualTo(String value) {
            addCriterion("commitDate >=", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateLessThan(String value) {
            addCriterion("commitDate <", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateLessThanOrEqualTo(String value) {
            addCriterion("commitDate <=", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateLike(String value) {
            addCriterion("commitDate like", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateNotLike(String value) {
            addCriterion("commitDate not like", value, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateIn(List<String> values) {
            addCriterion("commitDate in", values, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateNotIn(List<String> values) {
            addCriterion("commitDate not in", values, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateBetween(String value1, String value2) {
            addCriterion("commitDate between", value1, value2, "commitdate");
            return (Criteria) this;
        }

        public Criteria andCommitdateNotBetween(String value1, String value2) {
            addCriterion("commitDate not between", value1, value2, "commitdate");
            return (Criteria) this;
        }

        public Criteria andAuthoremailIsNull() {
            addCriterion("authorEmail is null");
            return (Criteria) this;
        }

        public Criteria andAuthoremailIsNotNull() {
            addCriterion("authorEmail is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoremailEqualTo(String value) {
            addCriterion("authorEmail =", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailNotEqualTo(String value) {
            addCriterion("authorEmail <>", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailGreaterThan(String value) {
            addCriterion("authorEmail >", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailGreaterThanOrEqualTo(String value) {
            addCriterion("authorEmail >=", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailLessThan(String value) {
            addCriterion("authorEmail <", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailLessThanOrEqualTo(String value) {
            addCriterion("authorEmail <=", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailLike(String value) {
            addCriterion("authorEmail like", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailNotLike(String value) {
            addCriterion("authorEmail not like", value, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailIn(List<String> values) {
            addCriterion("authorEmail in", values, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailNotIn(List<String> values) {
            addCriterion("authorEmail not in", values, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailBetween(String value1, String value2) {
            addCriterion("authorEmail between", value1, value2, "authoremail");
            return (Criteria) this;
        }

        public Criteria andAuthoremailNotBetween(String value1, String value2) {
            addCriterion("authorEmail not between", value1, value2, "authoremail");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
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

        public Criteria andSmellcountIsNull() {
            addCriterion("smellcount is null");
            return (Criteria) this;
        }

        public Criteria andSmellcountIsNotNull() {
            addCriterion("smellcount is not null");
            return (Criteria) this;
        }

        public Criteria andSmellcountEqualTo(Integer value) {
            addCriterion("smellcount =", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountNotEqualTo(Integer value) {
            addCriterion("smellcount <>", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountGreaterThan(Integer value) {
            addCriterion("smellcount >", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("smellcount >=", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountLessThan(Integer value) {
            addCriterion("smellcount <", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountLessThanOrEqualTo(Integer value) {
            addCriterion("smellcount <=", value, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountIn(List<Integer> values) {
            addCriterion("smellcount in", values, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountNotIn(List<Integer> values) {
            addCriterion("smellcount not in", values, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountBetween(Integer value1, Integer value2) {
            addCriterion("smellcount between", value1, value2, "smellcount");
            return (Criteria) this;
        }

        public Criteria andSmellcountNotBetween(Integer value1, Integer value2) {
            addCriterion("smellcount not between", value1, value2, "smellcount");
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