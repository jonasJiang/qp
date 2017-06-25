package smb.service.vo.zj;

import java.io.Serializable;

public class YiyuanVO implements Serializable {
    private String  areaName;       //省直
    private String  hospitalAddress;//杭州市上城区解放路88号
    private String  hospitalId;     //957102
    private String  hospitalLevel;  //31  
    private String  hospitalName;   // 浙二医院
    private String  hospitalQuality;//   
    private String  hospitalTele;   //(0571)87783777
    private String  hospitalType;   //  A1  
    private Integer orderSuccess;   // 809509;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalQuality() {
        return hospitalQuality;
    }

    public void setHospitalQuality(String hospitalQuality) {
        this.hospitalQuality = hospitalQuality;
    }

    public String getHospitalTele() {
        return hospitalTele;
    }

    public void setHospitalTele(String hospitalTele) {
        this.hospitalTele = hospitalTele;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }

    public Integer getOrderSuccess() {
        return orderSuccess;
    }

    public void setOrderSuccess(Integer orderSuccess) {
        this.orderSuccess = orderSuccess;
    }

}
