package smb.service.vo.zj;

import java.io.Serializable;
import java.util.List;

public class KeshiVODepts implements Serializable {
    private List<KeshiBigType> depts;

    public List<KeshiBigType> getDepts() {
        return depts;
    }

    public void setDepts(List<KeshiBigType> depts) {
        this.depts = depts;
    }
    
}
 