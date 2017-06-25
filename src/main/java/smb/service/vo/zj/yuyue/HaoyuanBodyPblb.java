package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.Map;

public class HaoyuanBodyPblb implements Serializable {
    private Map<String,HaoyuanDateList> list;

    public Map<String, HaoyuanDateList> getList() {
        return list;
    }

    public void setList(Map<String, HaoyuanDateList> list) {
        this.list = list;
    }

}
