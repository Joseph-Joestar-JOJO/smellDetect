package com.ruoyi.system.domain;

import generator.SmellcountKey;

import java.io.Serializable;

/**
 * smellcount
 * @author 
 */
public class Smellcount extends SmellcountKey implements Serializable {
    public String tag;

    private Integer blobcount;

    private Integer featureenvycount;

    private Integer longmethodcount;

    private Integer misplacedclasscount;

    private Integer promiscuouspackagecount;

    private String commitdate;

    private Integer blobchange;

    private Integer featureenvychange;

    private Integer longmethodchange;

    private Integer misplacedclasschange;

    private Integer promiscuouspackagechange;

    private static final long serialVersionUID = 1L;

    public String getTag() {
        return tag;
    }

    public void setTag(String Tag) {
        this.tag = Tag;
    }

    public Integer getBlobcount() {
        return blobcount;
    }

    public void setBlobcount(Integer blobcount) {
        this.blobcount = blobcount;
    }

    public Integer getFeatureenvycount() {
        return featureenvycount;
    }

    public void setFeatureenvycount(Integer featureenvycount) {
        this.featureenvycount = featureenvycount;
    }

    public Integer getLongmethodcount() {
        return longmethodcount;
    }

    public void setLongmethodcount(Integer longmethodcount) {
        this.longmethodcount = longmethodcount;
    }

    public Integer getMisplacedclasscount() {
        return misplacedclasscount;
    }

    public void setMisplacedclasscount(Integer misplacedclasscount) {
        this.misplacedclasscount = misplacedclasscount;
    }

    public Integer getPromiscuouspackagecount() {
        return promiscuouspackagecount;
    }

    public void setPromiscuouspackagecount(Integer promiscuouspackagecount) {
        this.promiscuouspackagecount = promiscuouspackagecount;
    }

    public String getCommitdate() {
        return commitdate;
    }

    public void setCommitdate(String commitdate) {
        this.commitdate = commitdate;
    }

    public Integer getBlobchange() {
        return blobchange;
    }

    public void setBlobchange(Integer blobchange) {
        this.blobchange = blobchange;
    }

    public Integer getFeatureenvychange() {
        return featureenvychange;
    }

    public void setFeatureenvychange(Integer featureenvychange) {
        this.featureenvychange = featureenvychange;
    }

    public Integer getLongmethodchange() {
        return longmethodchange;
    }

    public void setLongmethodchange(Integer longmethodchange) {
        this.longmethodchange = longmethodchange;
    }

    public Integer getMisplacedclasschange() {
        return misplacedclasschange;
    }

    public void setMisplacedclasschange(Integer misplacedclasschange) {
        this.misplacedclasschange = misplacedclasschange;
    }

    public Integer getPromiscuouspackagechange() {
        return promiscuouspackagechange;
    }

    public void setPromiscuouspackagechange(Integer promiscuouspackagechange) {
        this.promiscuouspackagechange = promiscuouspackagechange;
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
        Smellcount other = (Smellcount) that;
        return (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getBlobcount() == null ? other.getBlobcount() == null : this.getBlobcount().equals(other.getBlobcount()))
            && (this.getFeatureenvycount() == null ? other.getFeatureenvycount() == null : this.getFeatureenvycount().equals(other.getFeatureenvycount()))
            && (this.getLongmethodcount() == null ? other.getLongmethodcount() == null : this.getLongmethodcount().equals(other.getLongmethodcount()))
            && (this.getMisplacedclasscount() == null ? other.getMisplacedclasscount() == null : this.getMisplacedclasscount().equals(other.getMisplacedclasscount()))
            && (this.getPromiscuouspackagecount() == null ? other.getPromiscuouspackagecount() == null : this.getPromiscuouspackagecount().equals(other.getPromiscuouspackagecount()))
            && (this.getCommitdate() == null ? other.getCommitdate() == null : this.getCommitdate().equals(other.getCommitdate()))
            && (this.getBlobchange() == null ? other.getBlobchange() == null : this.getBlobchange().equals(other.getBlobchange()))
            && (this.getFeatureenvychange() == null ? other.getFeatureenvychange() == null : this.getFeatureenvychange().equals(other.getFeatureenvychange()))
            && (this.getLongmethodchange() == null ? other.getLongmethodchange() == null : this.getLongmethodchange().equals(other.getLongmethodchange()))
            && (this.getMisplacedclasschange() == null ? other.getMisplacedclasschange() == null : this.getMisplacedclasschange().equals(other.getMisplacedclasschange()))
            && (this.getPromiscuouspackagechange() == null ? other.getPromiscuouspackagechange() == null : this.getPromiscuouspackagechange().equals(other.getPromiscuouspackagechange()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getBlobcount() == null) ? 0 : getBlobcount().hashCode());
        result = prime * result + ((getFeatureenvycount() == null) ? 0 : getFeatureenvycount().hashCode());
        result = prime * result + ((getLongmethodcount() == null) ? 0 : getLongmethodcount().hashCode());
        result = prime * result + ((getMisplacedclasscount() == null) ? 0 : getMisplacedclasscount().hashCode());
        result = prime * result + ((getPromiscuouspackagecount() == null) ? 0 : getPromiscuouspackagecount().hashCode());
        result = prime * result + ((getCommitdate() == null) ? 0 : getCommitdate().hashCode());
        result = prime * result + ((getBlobchange() == null) ? 0 : getBlobchange().hashCode());
        result = prime * result + ((getFeatureenvychange() == null) ? 0 : getFeatureenvychange().hashCode());
        result = prime * result + ((getLongmethodchange() == null) ? 0 : getLongmethodchange().hashCode());
        result = prime * result + ((getMisplacedclasschange() == null) ? 0 : getMisplacedclasschange().hashCode());
        result = prime * result + ((getPromiscuouspackagechange() == null) ? 0 : getPromiscuouspackagechange().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blobcount=").append(blobcount);
        sb.append(", featureenvycount=").append(featureenvycount);
        sb.append(", longmethodcount=").append(longmethodcount);
        sb.append(", misplacedclasscount=").append(misplacedclasscount);
        sb.append(", promiscuouspackagecount=").append(promiscuouspackagecount);
        sb.append(", commitdate=").append(commitdate);
        sb.append(", blobchange=").append(blobchange);
        sb.append(", featureenvychange=").append(featureenvychange);
        sb.append(", longmethodchange=").append(longmethodchange);
        sb.append(", misplacedclasschange=").append(misplacedclasschange);
        sb.append(", promiscuouspackagechange=").append(promiscuouspackagechange);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}