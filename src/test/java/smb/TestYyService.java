package smb;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import smb.service.entity.KeshiDO;
import smb.service.entity.YyDO;
import smb.service.impl.KeshiService;
import smb.service.impl.YishengService;
import smb.service.impl.YuyueService;
import smb.service.impl.YyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class TestYyService {

    @Autowired
    private YyService yyservice;
    @Autowired
    private KeshiService ksservice;
    @Autowired
    private YishengService ysservice;
    @Autowired
    private YuyueService yuyueservice;

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
    ExecutorService exec = Executors.newCachedThreadPool();   

    
    @Test
    public void testinitdata(){
        yyservice.getYiyuanlistFromUrl();
        List<YyDO> list=yyservice.findList();
        if (list!=null){
            for(YyDO entity:list){
                //取科室list
                ksservice.getKeshilistFromUrl(entity.getId(),entity.getYymc());            
            }
        }
    }

    @Test
    public void testgetYishenglistFromUrl(){
        List<KeshiDO> kslist =ksservice.findList();
        for(KeshiDO ks:kslist){
            ysservice.getYishenglistFromUrl(ks.getYyId(), ks.getYyMc(), ks.getKeshiId(), ks.getKeshiMc());
        }
    }
    @Test
    public void testgetJzxh(){
        yuyueservice.chooseJzxhFromUrl("20170607", "ws-957102-10380", "565B25B83E5307021C8956DBE9C77326", "1");
    }
    
    
}
