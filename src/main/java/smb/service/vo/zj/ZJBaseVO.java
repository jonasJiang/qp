package smb.service.vo.zj;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZJBaseVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5707647991745438280L;
    @JsonProperty("x-response-code")
    private int xResponseCode;
    @JsonProperty("x-response-msg")
    private String xResponseMsg  ;
    public int getxResponseCode() {
        return xResponseCode;
    }
    public void setxResponseCode(int xResponseCode) {
        this.xResponseCode = xResponseCode;
    }
    public String getxResponseMsg() {
        return xResponseMsg;
    }
    public void setxResponseMsg(String xResponseMsg) {
        this.xResponseMsg = xResponseMsg;
    }
}
