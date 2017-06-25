package smb.service.entity;

import java.util.Date;

import smb.service.dao.RequestEnum;

public class RequestDO extends BaseDO{


    /**
     * 
     */
    private static final long serialVersionUID = 8577010996699903245L;
    private int id;
    private String mobile;
    private String name;
    private String idcard;
    private Date wantDate;
    private String yyId;
    private String keshiId;
    private String ysId;
    private Date qpDate;
    private String yyType="sheng";
    private RequestEnum status;
    private String note;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
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
    public Date getQpDate() {
        return qpDate;
    }
    public void setQpDate(Date qpDate) {
        this.qpDate = qpDate;
    }
    public String getYyType() {
        return yyType;
    }
    public void setYyType(String yyType) {
        this.yyType = yyType;
    }
    public RequestEnum getStatus() {
        return status;
    }
    public void setStatus(RequestEnum status) {
        this.status = status;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

}
