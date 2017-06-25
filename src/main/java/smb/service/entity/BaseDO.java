package smb.service.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5044731165659882942L;
    private Date              createTime;
    private Date              updateTime;
  

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
