package smb.service.vo.zj.yuyue;

import java.io.Serializable;
import java.util.List;

public class JzhBodyVO implements Serializable {

    private int         count;

    private List<JzhVO> hylb;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<JzhVO> getHylb() {
        return hylb;
    }

    public void setHylb(List<JzhVO> hylb) {
        this.hylb = hylb;
    }

}
