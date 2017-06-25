package smb.service.vo.zj.yuyue;

import java.io.Serializable;

public class HaoyuanInfo implements Serializable {
    private HaoyuanVO swh;
    private HaoyuanVO xwh;
    public HaoyuanVO getSwh() {
        return swh;
    }
    public void setSwh(HaoyuanVO swh) {
        this.swh = swh;
    }
    public HaoyuanVO getXwh() {
        return xwh;
    }
    public void setXwh(HaoyuanVO xwh) {
        this.xwh = xwh;
    }
    
}
