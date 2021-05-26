package generator;

import java.io.Serializable;

/**
 * project
 * @author 
 */
public class ProjectKey implements Serializable {
    private String projurl;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public String getProjurl() {
        return projurl;
    }

    public void setProjurl(String projurl) {
        this.projurl = projurl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}