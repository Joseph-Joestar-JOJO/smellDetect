package generator;

import java.io.Serializable;

/**
 * smellcount
 * @author 
 */
public class Smellcount extends SmellcountKey implements Serializable {
    private Integer blobcount;

    private Integer featureenvycount;

    private Integer longmethodcount;

    private Integer misplacedclasscount;

    private Integer promiscuouspackagecount;

    private static final long serialVersionUID = 1L;

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
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getBlobcount() == null ? other.getBlobcount() == null : this.getBlobcount().equals(other.getBlobcount()))
            && (this.getFeatureenvycount() == null ? other.getFeatureenvycount() == null : this.getFeatureenvycount().equals(other.getFeatureenvycount()))
            && (this.getLongmethodcount() == null ? other.getLongmethodcount() == null : this.getLongmethodcount().equals(other.getLongmethodcount()))
            && (this.getMisplacedclasscount() == null ? other.getMisplacedclasscount() == null : this.getMisplacedclasscount().equals(other.getMisplacedclasscount()))
            && (this.getPromiscuouspackagecount() == null ? other.getPromiscuouspackagecount() == null : this.getPromiscuouspackagecount().equals(other.getPromiscuouspackagecount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getBlobcount() == null) ? 0 : getBlobcount().hashCode());
        result = prime * result + ((getFeatureenvycount() == null) ? 0 : getFeatureenvycount().hashCode());
        result = prime * result + ((getLongmethodcount() == null) ? 0 : getLongmethodcount().hashCode());
        result = prime * result + ((getMisplacedclasscount() == null) ? 0 : getMisplacedclasscount().hashCode());
        result = prime * result + ((getPromiscuouspackagecount() == null) ? 0 : getPromiscuouspackagecount().hashCode());
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}