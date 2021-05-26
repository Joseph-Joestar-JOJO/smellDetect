package generator;

import java.io.Serializable;

/**
 * projmember
 * @author 
 */
public class Projmember extends ProjmemberKey implements Serializable {
    private String membername;

    private String projname;

    private static final long serialVersionUID = 1L;

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
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
        Projmember other = (Projmember) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getMemberid() == null ? other.getMemberid() == null : this.getMemberid().equals(other.getMemberid()))
            && (this.getMembername() == null ? other.getMembername() == null : this.getMembername().equals(other.getMembername()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getMemberid() == null) ? 0 : getMemberid().hashCode());
        result = prime * result + ((getMembername() == null) ? 0 : getMembername().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", membername=").append(membername);
        sb.append(", projname=").append(projname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}