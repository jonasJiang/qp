package smb.service.entity;

import java.util.Date;

public class ResultDO extends BaseDO{


    /**
     * 
     */
    private static final long serialVersionUID = 8577010996699903245L;
    private String mobile;
    private String name;
    private String idcard;
    private Date wantDate;
    private String yyId;
    private String keshiId;
    private String ysId;
    private String qhmm;
    private String ddid;
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public Date getWantDate() {
        return wantDate;
    }
    public void setWantDate(Date wantDate) {
        this.wantDate = wantDate;
    }
    public String getYyId() {
        return yyId;
    }
    public void setYyId(String yyId) {
        this.yyId = yyId;
    }
    public String getKeshiId() {
        return keshiId;
    }
    public void setKeshiId(String keshiId) {
        this.keshiId = keshiId;
    }
    public String getYsId() {
        return ysId;
    }
    public void setYsId(String ysId) {
        this.ysId = ysId;
    }
    public String getQhmm() {
        return qhmm;
    }
    public void setQhmm(String qhmm) {
        this.qhmm = qhmm;
    }
    public String getDdid() {
        return ddid;
    }
    public void setDdid(String ddid) {
        this.ddid = ddid;
    }
     

}
