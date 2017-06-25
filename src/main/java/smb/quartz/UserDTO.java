package smb.quartz;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3056719695709620540L;
    private String idcard;
    private String name;
    private String mobile;
    private Date   logintime;
    private String token;
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Date getLogintime() {
        return logintime;
    }
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
}
