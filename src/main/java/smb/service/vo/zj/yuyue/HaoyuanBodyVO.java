package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HaoyuanBodyVO implements Serializable {

    private int count;
//    private Map<String, List<DepartmentDoctorPaibanInfo>> pblb;

  private Map<String, List<HaoyuanDateList>> pblb;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Map<String, List<HaoyuanDateList>> getPblb() {
        return pblb;
    }
    public void setPblb(Map<String, List<HaoyuanDateList>> pblb) {
        this.pblb = pblb;
    }

    
}
