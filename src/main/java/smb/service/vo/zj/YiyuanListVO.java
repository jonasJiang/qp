package smb.service.vo.zj;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YiyuanListVO extends ZJBaseVO {
    
    @JsonProperty("data_list")
    private List<YiyuanVO> datalist;

    public List<YiyuanVO> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<YiyuanVO> datalist) {
        this.datalist = datalist;
    }
    

}

