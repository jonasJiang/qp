
package smb.ctl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import smb.service.entity.RequestDO;
import smb.service.impl.RequestService;

@RestController
public class RequestCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestService service;
    
    @RequestMapping("/")
    @ResponseBody
    public String request(String mobile,String idcard,Date wantDate,String yyId,String ysId,String keshiId){
        
        
        RequestDO entity  =service.findByUser(idcard, wantDate, yyId, ysId, keshiId);
        if(entity==null || entity.getId()!=0){
            logger.error("have record ,please not repeat commit {},{},{},{},{}",idcard, wantDate, yyId, ysId, keshiId);
        }
        entity =new RequestDO();
        entity.setMobile(mobile);
        entity.setIdcard(idcard);
        entity.setWantDate(wantDate);
        entity.setYyId(yyId);
        entity.setYsId(ysId);
        entity.setKeshiId(keshiId);
        int rst = service.insert(entity);
        if(rst!=1){
            logger.error("service.insert not success {},{},{},{},{}",idcard, wantDate, yyId, ysId, keshiId);
        }
        return "success";
    }
    
}
