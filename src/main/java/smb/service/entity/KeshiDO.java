package smb.service.entity;

public class KeshiDO extends BaseDO {

    /**
     * 
     */
    private static final long serialVersionUID = -5997291776671182755L;
    
    private String keshiId;
    private String keshiMc;
    private String yyId;
    private String yyMc;

    public String getKeshiId() {
        return keshiId;
    }

    public void setKeshiId(String keshiId) {
        this.keshiId = keshiId;
    }

    public String getKeshiMc() {
        return keshiMc;
    }

    public void setKeshiMc(String keshiMc) {
        this.keshiMc = keshiMc;
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
