package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.List;

public class HaoyuanKeshi implements Serializable {

    private String date;
    private List<HaoyuanInfo> info;
    private String week ;
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
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
}
