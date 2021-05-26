package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * featureenvy
 * @author 
 */
public class FeatureenvyKey implements Serializable {
    private String projurl;

    private String commithash;

    private Date occurtime;

    private String methodname;

    private static final long serialVersionUID = 1L;

    public String getProjurl() {
        return projurl;
    }

    public void setProjurl(String projurl) {
        this.projurl = projurl;
    }

    public String getCommithash() {
        return commithash;
    }

    public void setCommithash(String commithash) {
        this.commithash = commithash;
    }

    public Date getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(Date occurtime) {
        this.occurtime = occurtime;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
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
        FeatureenvyKey other = (FeatureenvyKey) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getOccurtime() == null ? other.getOccurtime() == null : this.getOccurtime().equals(other.getOccurtime()))
            && (this.getMethodname() == null ? other.getMethodname() == null : this.getMethodname().equals(other.getMethodname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getOccurtime() == null) ? 0 : getOccurtime().hashCode());
        result = prime * result + ((getMethodname() == null) ? 0 : getMethodname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projurl=").append(projurl);
        sb.append(", commithash=").append(commithash);
        sb.append(", occurtime=").append(occurtime);
        sb.append(", methodname=").append(methodname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}