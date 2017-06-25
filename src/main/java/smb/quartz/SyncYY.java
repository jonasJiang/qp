package smb.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import smb.service.dao.RequestEnum;
import smb.service.entity.RequestDO;
import smb.service.impl.RequestService;

@Component
public class SyncYY {
    private final Logger             logger   = LoggerFactory.getLogger(this.getClass());

    private ExecutorService          es       = Executors.newCachedThreadPool();
    private BlockingQueue<RequestDO> queue    = new LinkedBlockingQueue<RequestDO>(1000);
    private HashMap<String, UserDTO> tokenmap = new HashMap<String, UserDTO>();
    @Autowired
    private RequestService           requestservice;

    public static void main(String[] args) {
        System.out.println(DateUtils.round(new Date(), Calendar.DATE));
    }

    @Scheduled(cron = "0 0/1 * * * ?") //每分钟执行一次        0 15 10 ? * * //每天上午10:15触发
    public void syncyy() {
        logger.info("每分钟执行一次。开始……");
        Date today = new Date();

        logger.info("时间：{}",
            DateFormatUtils.ISO_DATE_FORMAT.format(DateUtils.round(today, Calendar.DATE)));
        //1  需要抢票的人员信息
        List<RequestDO> list = requestservice.findNeedList(DateUtils.round(today, Calendar.DATE));
        logger.info("需求size：{}", list.size());
        //2 人员账号登录，保存token
        for (RequestDO en : list) {
            if (!tokenmap.containsKey(en.getIdcard())) {

            }
        }
        //        Assert.assertNotNull(entity2);
        //        Assert.assertEquals(entity2.getId(), 222);
        //        Assert.assertEquals(entity2.getYymc(), "浙医二院");
        //statusTask.healthCheck();  
        //        logger.info("每分钟执行一次。结束。");  
    }

    /**
     * 
     * @param idcard
     */
    public void dologin(String idcard, HashMap<String, String> tokenmap) {

    }

    public void doqp() {
        Date today = new Date();
        List<RequestDO> list = requestservice.findNeedList(DateUtils.round(today, Calendar.DATE));
        logger.info("需求size：{}", list.size());
        boolean flag = true;
        for (RequestDO entity : list) {
            //TODO qiangpiao
        }

        list = requestservice.findNeedList(DateUtils.round(today, Calendar.DATE));
        for (RequestDO entity : list) {
            if (entity.getStatus().equals(RequestEnum.waiting)) {
                flag = false;
            }
        }

    }

}
