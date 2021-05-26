package com.ruoyi.system.domain;

import generator.SmellKey;

import java.io.Serializable;
import java.util.Date;

/**
 * smell
 * @author 
 */
public class Smell extends SmellKey implements Serializable {
    private Date occurtime;

    private String projname;

    private Date eliminatetime;

    private String filepath;

    private String catalog;

    private String smellinfo;

    private String content;

    private static final long serialVersionUID = 1L;

    public Date getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(Date occurtime) {
        this.occurtime = occurtime;
    }

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public Date getEliminatetime() {
        return eliminatetime;
    }

    public void setEliminatetime(Date eliminatetime) {
        this.eliminatetime = eliminatetime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSmellinfo() {
        return smellinfo;
    }

    public void setSmellinfo(String smellinfo) {
        this.smellinfo = smellinfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        Smell other = (Smell) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOccurtime() == null ? other.getOccurtime() == null : this.getOccurtime().equals(other.getOccurtime()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()))
            && (this.getEliminatetime() == null ? other.getEliminatetime() == null : this.getEliminatetime().equals(other.getEliminatetime()))
            && (this.getFilepath() == null ? other.getFilepath() == null : this.getFilepath().equals(other.getFilepath()))
            && (this.getCatalog() == null ? other.getCatalog() == null : this.getCatalog().equals(other.getCatalog()))
            && (this.getSmellinfo() == null ? other.getSmellinfo() == null : this.getSmellinfo().equals(other.getSmellinfo()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOccurtime() == null) ? 0 : getOccurtime().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        result = prime * result + ((getEliminatetime() == null) ? 0 : getEliminatetime().hashCode());
        result = prime * result + ((getFilepath() == null) ? 0 : getFilepath().hashCode());
        result = prime * result + ((getCatalog() == null) ? 0 : getCatalog().hashCode());
        result = prime * result + ((getSmellinfo() == null) ? 0 : getSmellinfo().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", occurtime=").append(occurtime);
        sb.append(", projname=").append(projname);
        sb.append(", eliminatetime=").append(eliminatetime);
        sb.append(", filepath=").append(filepath);
        sb.append(", catalog=").append(catalog);
        sb.append(", smellinfo=").append(smellinfo);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}