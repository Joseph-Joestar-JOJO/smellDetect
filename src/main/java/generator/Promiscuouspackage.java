package generator;

import java.io.Serializable;

/**
 * promiscuouspackage
 * @author 
 */
public class Promiscuouspackage extends PromiscuouspackageKey implements Serializable {
    private String projname;

    private String eliminatetime;

    private static final long serialVersionUID = 1L;

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public String getEliminatetime() {
        return eliminatetime;
    }

    public void setEliminatetime(String eliminatetime) {
        this.eliminatetime = eliminatetime;
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
        Promiscuouspackage other = (Promiscuouspackage) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getPackagename() == null ? other.getPackagename() == null : this.getPackagename().equals(other.getPackagename()))
            && (this.getOccurtime() == null ? other.getOccurtime() == null : this.getOccurtime().equals(other.getOccurtime()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()))
            && (this.getEliminatetime() == null ? other.getEliminatetime() == null : this.getEliminatetime().equals(other.getEliminatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getPackagename() == null) ? 0 : getPackagename().hashCode());
        result = prime * result + ((getOccurtime() == null) ? 0 : getOccurtime().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        result = prime * result + ((getEliminatetime() == null) ? 0 : getEliminatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projname=").append(projname);
        sb.append(", eliminatetime=").append(eliminatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}