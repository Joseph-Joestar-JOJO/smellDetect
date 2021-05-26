package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * promiscuouspackage
 * @author 
 */
public class PromiscuouspackageKey implements Serializable {
    private Integer projurl;

    private String commithash;

    private String packagename;

    private Date occurtime;

    private static final long serialVersionUID = 1L;

    public Integer getProjurl() {
        return projurl;
    }

    public void setProjurl(Integer projurl) {
        this.projurl = projurl;
    }

    public String getCommithash() {
        return commithash;
    }

    public void setCommithash(String commithash) {
        this.commithash = commithash;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public Date getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(Date occurtime) {
        this.occurtime = occurtime;
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
        PromiscuouspackageKey other = (PromiscuouspackageKey) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getPackagename() == null ? other.getPackagename() == null : this.getPackagename().equals(other.getPackagename()))
            && (this.getOccurtime() == null ? other.getOccurtime() == null : this.getOccurtime().equals(other.getOccurtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getPackagename() == null) ? 0 : getPackagename().hashCode());
        result = prime * result + ((getOccurtime() == null) ? 0 : getOccurtime().hashCode());
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
        sb.append(", packagename=").append(packagename);
        sb.append(", occurtime=").append(occurtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}