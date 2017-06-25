package smb.service.entity;

public class YishengDO extends BaseDO{
    /**
     * 
     */
    private static final long serialVersionUID = -3319053185693152982L;
    private int id;
    private String keshiId;
    private String keshiMc;
    private String ysId;
    private String ysMc;
    private String ysTitle;
    private String ysImageUrl;
    private String feature;
    private String orderCount;
    private String yyId;
    private String yyMc;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKeshiId() {
        return keshiId;
    }
    
    public String getKeshiMc() {
        return keshiMc;
    }
    public void setKeshiMc(String keshiMc) {
        this.keshiMc = keshiMc;
    }
    public void setKeshiId(String keshiId) {
        this.keshiId = keshiId;
    }
    
    public String getYsTitle() {
        return ysTitle;
    }
    public void setYsTitle(String ysTitle) {
        this.ysTitle = ysTitle;
    }
    public String getYsImageUrl() {
        return ysImageUrl;
    }
    public void setYsImageUrl(String ysImageUrl) {
        this.ysImageUrl = ysImageUrl;
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
    public String getYsId() {
        return ysId;
    }
    public void setYsId(String ysId) {
        this.ysId = ysId;
    }
    public String getYsMc() {
        return ysMc;
    }
    public void setYsMc(String ysMc) {
        this.ysMc = ysMc;
    }
    public String getYyId() {
        return yyId;
    }
    public void setYyId(String yyId) {
        this.yyId = yyId;
    }
    public String getYyMc() {
        return yyMc;
    }
    public void setYyMc(String yyMc) {
        this.yyMc = yyMc;
    }
    
}
