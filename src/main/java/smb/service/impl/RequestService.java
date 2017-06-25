package smb.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import smb.service.dao.RequestDao;
import smb.service.dao.RequestEnum;
import smb.service.entity.RequestDO;

@Service
public class RequestService {
    @Autowired
    RestTemplate           restTemplate;

    @Autowired
    private RequestDao     requestdao;
    @Autowired
    private YyService      yyservice;
    @Autowired
    private YishengService ysservice;
    @Autowired
    private KeshiService   ksservice;

    @Transactional
    public void insertRequest(List<RequestDO> list) {
        for (RequestDO entity : list) {
            this.insert(entity);
        }
    }

    /**
     * 当日需要抢票的
     * @param date
     * @return
     */
    public List<RequestDO> findNeedList(Date today) {
        return requestdao.findNeedList(today, RequestEnum.waiting);
    }

    public int insert(RequestDO entity) {
        Assert.notNull(entity, "entity is null");
        if (!entity.getYyType().equals("sheng")) {
            entity.setQpDate(DateUtils.addDays(entity.getWantDate(), -1));//市级医院，抢票日期为date-1
        } else {
            entity.setQpDate(DateUtils.addDays(entity.getWantDate(), -7));//sheng级医院，抢票日期为date-7

        }
        return requestdao.insert(entity);
    }

    public RequestDO findById(int id) {
        return requestdao.findById(id);
    }

    public RequestDO findByUser(String idcard, Date wantDate, String yyId, String ysId,
                                String keshiId) {
        return requestdao.findByUser(idcard, wantDate, yyId, ysId, keshiId);
    }
}
