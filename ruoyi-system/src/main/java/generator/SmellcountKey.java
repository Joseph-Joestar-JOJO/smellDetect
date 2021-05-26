package generator;

import java.io.Serializable;

/**
 * smellcount
 * @author 
 */
public class SmellcountKey implements Serializable {
    private String commithash;

    private String projurl;

    private static final long serialVersionUID = 1L;

    public String getCommithash() {
        return commithash;
    }

    public void setCommithash(String commithash) {
        this.commithash = commithash;
    }

    public String getProjurl() {
        return projurl;
    }

    public void setProjurl(String projurl) {
        this.projurl = projurl;
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
        SmellcountKey other = (SmellcountKey) that;
        return (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commithash=").append(commithash);
        sb.append(", projurl=").append(projurl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}