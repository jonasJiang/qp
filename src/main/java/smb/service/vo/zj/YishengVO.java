package smb.service.vo.zj;

import java.io.Serializable;

public class YishengVO implements Serializable {
    private String  doctorHaoyuanStatus;       //
    private String  doctorId;//
    private String  doctorImageUrl;     //
    private String  doctorName;  //  
    private String  doctorTitle;   // 
    private String  feature;//   
    private String  orderCount;   //
    private String  score;   //  90.0  
    public String getDoctorHaoyuanStatus() {
        return doctorHaoyuanStatus;
    }
    public void setDoctorHaoyuanStatus(String doctorHaoyuanStatus) {
        this.doctorHaoyuanStatus = doctorHaoyuanStatus;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorImageUrl() {
        return doctorImageUrl;
    }
    public void setDoctorImageUrl(String doctorImageUrl) {
        this.doctorImageUrl = doctorImageUrl;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getDoctorTitle() {
        return doctorTitle;
    }
    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
   
}
