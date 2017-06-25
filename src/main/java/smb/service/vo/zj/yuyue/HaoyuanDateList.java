package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.List;

public class HaoyuanDateList implements Serializable {
    private String date;
    private List<HaoyuanInfo> info;
//    private List<HaoyuanKeshi> info;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public List<HaoyuanInfo> getInfo() {
        return info;
    }
    public void setInfo(List<HaoyuanInfo> info) {
        this.info = info;
    }


    
    
    
}
