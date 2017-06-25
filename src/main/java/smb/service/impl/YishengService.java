package smb.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import smb.service.dao.YishengDao;
import smb.service.entity.YishengDO;
import smb.service.vo.zj.YishengListVOFromUrl;
import smb.service.vo.zj.YishengVO;
import smb.util.NetUtil;

@Service
public class YishengService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  

    @Autowired
    RestTemplate           restTemplate;

    @Autowired
    private YishengDao     ysdao;

    /**
     * 根据医院id+科室id，取医生信息，入库
     */
    public void getYishenglistFromUrl(String yyId,String yyMc, String keshiId, String keshiMc) {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("yyId", yyId);
            map.put("keshiId", keshiId);
            String url = NetUtil.appurl_zj_zhy + "/getdepDoctor/{yyId}/{keshiId}?json=format";
            YishengListVOFromUrl json = restTemplate.getForObject(url, YishengListVOFromUrl.class,map);
            updateYishengInfo(json, yyId,yyMc, keshiId,keshiMc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateYishengInfo(YishengListVOFromUrl json,String yyId,String yyMc, String keshiId, String keshiMc) {
        if (json.getxResponseCode() == 200) {
            logger.info("begin yiyuan:{},{},keshi:{},{}",yyId,yyMc,keshiId,keshiMc);

            ysdao.deleteByYyIdKeshiId(yyId, keshiId);
            for (YishengVO vo : json.getBody().getDatalist()) {
                if(StringUtils.isBlank(vo.getDoctorName())){
                    continue;
                }
                YishengDO entity = new YishengDO();
                entity.setKeshiId(keshiId);
                entity.setKeshiMc(keshiMc);
                entity.setYyId(yyId);
                entity.setYyMc(yyMc);
                entity.setYsTitle(vo.getDoctorTitle());
                entity.setYsImageUrl(vo.getDoctorImageUrl());
                entity.setFeature(StringUtils.stripToEmpty(vo.getFeature()));
                entity.setOrderCount(StringUtils.stripToEmpty(vo.getOrderCount()));
                entity.setYsId(vo.getDoctorId());
                entity.setYsMc(vo.getDoctorName());
                ysdao.insert(entity);
            }
        }

    }

    public int insert(YishengDO entity) {
        Assert.notNull(entity, "entity is null");
        return ysdao.insert(entity);
    }

    public YishengDO findById(int id) {
        return ysdao.findById(id);
    }

    public void deleteByYyIdKeshiId(String yyId, String keshiId) {
        ysdao.deleteByYyIdKeshiId(yyId,keshiId);
    }
}
