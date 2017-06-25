package smb.ctl;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 统一封装json数据对象
 * 
 * json格式
    成功
    返回结果
    {code:0000}
    返回数组
    {code:0000,data:[xxx,yyy]}
    返回对象数组
    {code:0000,data:[{xxx:yyy,aaa:bbb}]}
    返回对象
    {code:0000,data:{xxx:yyy}}
    返回对象包含列表(用于分页)
    {code:0000,data:{xxx:yyy,list:[]}}
    eg:分页
    {code:0000,data:{pagesize:8,page:1,totalpage:9,list:[{xxx:yyy,aaa:bbb}]}}
    示例代码：setData(com.lljz.p2p.vo.Page)
    
    失败
    返回错误
    {code:0001,errormsg:'xxxx'}
    eg:登录失败 
    {code:0001,errormsg:'登录已失效,请重新登录'}

 * */
@JsonInclude(Include.NON_NULL)
public class JsonObj implements Serializable {

    /**
     * serialize
     */
    private static final long serialVersionUID = 400496091806953754L;
    private String            code             = "0000";
    private String            msg;
    private Object            data;

    public JsonObj() {
    }

    public JsonObj(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonObj(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(ErrorCodes errorCodes) {
        this.setCode(errorCodes.getCode());
        this.setMsg(errorCodes.getMsg());
    }

}
