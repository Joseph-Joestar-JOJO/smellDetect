package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * misplacedclass
 * @author 
 */
public class Misplacedclass extends MisplacedclassKey implements Serializable {
    private Date eliminatetime;

    private String projname;

    private static final long serialVersionUID = 1L;

    public Date getEliminatetime() {
        return eliminatetime;
    }

    public void setEliminatetime(Date eliminatetime) {
        this.eliminatetime = eliminatetime;
    }

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
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
        Misplacedclass other = (Misplacedclass) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getOccurtime() == null ? other.getOccurtime() == null : this.getOccurtime().equals(other.getOccurtime()))
            && (this.getEliminatetime() == null ? other.getEliminatetime() == null : this.getEliminatetime().equals(other.getEliminatetime()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getOccurtime() == null) ? 0 : getOccurtime().hashCode());
        result = prime * result + ((getEliminatetime() == null) ? 0 : getEliminatetime().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eliminatetime=").append(eliminatetime);
        sb.append(", projname=").append(projname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}