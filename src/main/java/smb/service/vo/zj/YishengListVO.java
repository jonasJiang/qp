package smb.service.vo.zj;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YishengListVO extends ZJBaseVO {
    
    @JsonProperty("doc_info")
    private List<YishengVO> datalist;

    public List<YishengVO> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<YishengVO> datalist) {
        this.datalist = datalist;
    }
    

}

