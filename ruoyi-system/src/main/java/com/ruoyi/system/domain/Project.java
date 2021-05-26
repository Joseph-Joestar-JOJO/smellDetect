package com.ruoyi.system.domain;

import java.io.Serializable;

/**
 * project
 * @author 
 */
public class Project implements Serializable {
    private Integer projid;

    private String projurl;

    private String projname;

    private String linecount;

    private String status;

    private String creater;

    private String manager;

    private String membercount;

    private String projpath;

    private static final long serialVersionUID = 1L;

    public Integer getProjid() {
        return projid;
    }

    public void setProjid(Integer projid) {
        this.projid = projid;
    }

    public String getProjurl() {
        return projurl;
    }

    public void setProjurl(String projurl) {
        this.projurl = projurl;
    }

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public String getLinecount() {
        return linecount;
    }

    public void setLinecount(String linecount) {
        this.linecount = linecount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMembercount() {
        return membercount;
    }

    public void setMembercount(String membercount) {
        this.membercount = membercount;
    }

    public String getProjpath() {
        return projpath;
    }

    public void setProjpath(String projpath) {
        this.projpath = projpath;
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
        Project other = (Project) that;
        return (this.getProjid() == null ? other.getProjid() == null : this.getProjid().equals(other.getProjid()))
            && (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()))
            && (this.getLinecount() == null ? other.getLinecount() == null : this.getLinecount().equals(other.getLinecount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreater() == null ? other.getCreater() == null : this.getCreater().equals(other.getCreater()))
            && (this.getManager() == null ? other.getManager() == null : this.getManager().equals(other.getManager()))
            && (this.getMembercount() == null ? other.getMembercount() == null : this.getMembercount().equals(other.getMembercount()))
            && (this.getProjpath() == null ? other.getProjpath() == null : this.getProjpath().equals(other.getProjpath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjid() == null) ? 0 : getProjid().hashCode());
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        result = prime * result + ((getLinecount() == null) ? 0 : getLinecount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreater() == null) ? 0 : getCreater().hashCode());
        result = prime * result + ((getManager() == null) ? 0 : getManager().hashCode());
        result = prime * result + ((getMembercount() == null) ? 0 : getMembercount().hashCode());
        result = prime * result + ((getProjpath() == null) ? 0 : getProjpath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projid=").append(projid);
        sb.append(", projurl=").append(projurl);
        sb.append(", projname=").append(projname);
        sb.append(", linecount=").append(linecount);
        sb.append(", status=").append(status);
        sb.append(", creater=").append(creater);
        sb.append(", manager=").append(manager);
        sb.append(", membercount=").append(membercount);
        sb.append(", projpath=").append(projpath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}