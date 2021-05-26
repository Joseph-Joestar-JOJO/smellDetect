package generator;

import java.io.Serializable;

/**
 * projcommit
 * @author 
 */
public class Projcommit extends ProjcommitKey implements Serializable {
    private String projname;

    private String commitauthor;

    private String commitdate;

    private static final long serialVersionUID = 1L;

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public String getCommitauthor() {
        return commitauthor;
    }

    public void setCommitauthor(String commitauthor) {
        this.commitauthor = commitauthor;
    }

    public String getCommitdate() {
        return commitdate;
    }

    public void setCommitdate(String commitdate) {
        this.commitdate = commitdate;
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
        Projcommit other = (Projcommit) that;
        return (this.getProjurl() == null ? other.getProjurl() == null : this.getProjurl().equals(other.getProjurl()))
            && (this.getCommithash() == null ? other.getCommithash() == null : this.getCommithash().equals(other.getCommithash()))
            && (this.getProjname() == null ? other.getProjname() == null : this.getProjname().equals(other.getProjname()))
            && (this.getCommitauthor() == null ? other.getCommitauthor() == null : this.getCommitauthor().equals(other.getCommitauthor()))
            && (this.getCommitdate() == null ? other.getCommitdate() == null : this.getCommitdate().equals(other.getCommitdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjurl() == null) ? 0 : getProjurl().hashCode());
        result = prime * result + ((getCommithash() == null) ? 0 : getCommithash().hashCode());
        result = prime * result + ((getProjname() == null) ? 0 : getProjname().hashCode());
        result = prime * result + ((getCommitauthor() == null) ? 0 : getCommitauthor().hashCode());
        result = prime * result + ((getCommitdate() == null) ? 0 : getCommitdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projname=").append(projname);
        sb.append(", commitauthor=").append(commitauthor);
        sb.append(", commitdate=").append(commitdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}