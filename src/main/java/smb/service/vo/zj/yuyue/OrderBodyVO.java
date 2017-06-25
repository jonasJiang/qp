package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.List;

public class OrderBodyVO implements Serializable {
    private String         code;
    private String         ddid;
    private String         qhmm;
    private String         returnTime;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDdid() {
        return ddid;
    }
    public void setDdid(String ddid) {
        this.ddid = ddid;
    }
    public String getQhmm() {
        return qhmm;
    }
    public void setQhmm(String qhmm) {
        this.qhmm = qhmm;
    }
    public String getReturnTime() {
        return returnTime;
    }
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
    @Override
    public String toString() {
        return this.qhmm + this.ddid;
    }

}
