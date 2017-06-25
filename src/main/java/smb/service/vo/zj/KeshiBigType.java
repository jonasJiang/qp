package smb.service.vo.zj;

import java.io.Serializable;
import java.util.List;

public class KeshiBigType implements Serializable {

    private String name;
    private List<Keshi> childs;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Keshi> getChilds() {
        return childs;
    }
    public void setChilds(List<Keshi> childs) {
        this.childs = childs;
    }
    

}
