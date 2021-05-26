package com.ruoyi.system.domain;

import generator.ProjcommitKey;

import java.io.Serializable;

/**
 * projcommit
 * @author 
 */
public class Projcommit extends ProjcommitKey implements Serializable {
    private Integer projid;

    private String commitauthor;

    private String commitdate;

    private String authoremail;

    private String tag;

    private String status;

    private Integer smellcount;

    private static final long serialVersionUID = 1L;

    public Integer getProjid() {
        return projid;
    }

    public void setProjid(Integer projid) {
        this.projid = projid;
    }

    public String getCommitauthor() {
        return commitauthor;
    }

    public void setCommitauthor(String commitauthor) {
        this.commitauthor = commitauthor;
    }

    public String getCommitdate() {
        return commitdate;
    }

    public void setCommitdate(String commitdate) {
        this.commitdate = commitdate;
    }

    public String getAuthoremail() {
        return authoremail;
    }

    public void setAuthoremail(String authoremail) {
        this.authoremail = authoremail;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSmellcount() {
        return smellcount;
    }

    public void setSmellcount(Integer smellcount) {
        this.smellcount = smellcount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Projcommit other = (Projcommit) that;
        return (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getProjid() == null ? other.getProjid() == null : this.getProjid().equals(other.getProjid()))
            && (this.getCommitauthor() == null ? other.getCommitauthor() == null : this.getCommitauthor().equals(other.getCommitauthor()))
            && (this.getCommitdate() == null ? other.getCommitdate() == null : this.getCommitdate().equals(other.getCommitdate()))
            && (this.getAuthoremail() == null ? other.getAuthoremail() == null : this.getAuthoremail().equals(other.getAuthoremail()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSmellcount() == null ? other.getSmellcount() == null : this.getSmellcount().equals(other.getSmellcount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getProjid() == null) ? 0 : getProjid().hashCode());
        result = prime * result + ((getCommitauthor() == null) ? 0 : getCommitauthor().hashCode());
        result = prime * result + ((getCommitdate() == null) ? 0 : getCommitdate().hashCode());
        result = prime * result + ((getAuthoremail() == null) ? 0 : getAuthoremail().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSmellcount() == null) ? 0 : getSmellcount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projid=").append(projid);
        sb.append(", commitauthor=").append(commitauthor);
        sb.append(", commitdate=").append(commitdate);
        sb.append(", authoremail=").append(authoremail);
        sb.append(", tag=").append(tag);
        sb.append(", status=").append(status);
        sb.append(", smellcount=").append(smellcount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}