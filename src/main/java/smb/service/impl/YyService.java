package smb.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import smb.service.dao.YyDao;
import smb.service.entity.YishengDO;
import smb.service.entity.YyDO;
import smb.service.vo.zj.YishengListVOFromUrl;
import smb.service.vo.zj.YishengVO;
import smb.service.vo.zj.YiyuanListVOFromUrl;
import smb.service.vo.zj.YiyuanVO;
import smb.util.NetUtil;

@Service
public class YyService {
    @Autowired
    RestTemplate           restTemplate;
    
    @Autowired
    private YyDao          yydao;
    @Autowired
    private YishengService     ysservice;
    @Autowired
    private KeshiService       ksservice;

    /**
     * 取医院list，入库
     */
    public void getYiyuanlistFromUrl() {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            //        restTemplate.getForEntity(url, responseType, uriVariables)
            String url = NetUtil.appurl_zj_zhy
                         + "/hospital/hospitalsearch?hospitalLevel=&sort=&area=&json=format";
            YiyuanListVOFromUrl json = restTemplate.getForObject(url, YiyuanListVOFromUrl.class);
            updateYYInfo(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateYYInfo(YiyuanListVOFromUrl json) {
        if (json.getxResponseCode() == 200) {
            yydao.deleteall();
            for (YiyuanVO vo : json.getBody().getDatalist()) {
                YyDO entity = new YyDO();
                entity.setId(vo.getHospitalId());
                entity.setYymc(vo.getHospitalName());
                yydao.insert(entity);
            }
        }

    }


    /**
     * 根据医院id+科室id，取医生信息，入库
     */
    public void getYishenglistFromUrl(String yyId,String yyMc, String keshiId, String keshiMc) {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("yyId", yyId);
            map.put("keshiId", keshiId);
            //        restTemplate.getForEntity(url, responseType, uriVariables)
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
            ysservice.deleteByYyIdKeshiId(yyId,keshiId);
            for (YishengVO vo : json.getBody().getDatalist()) {
                YishengDO entity = new YishengDO();
                entity.setKeshiId(keshiId);
                entity.setKeshiMc(keshiMc);
                entity.setYyId(yyId);
                entity.setYyMc(yyMc);
                entity.setYsTitle(vo.getDoctorTitle());
                entity.setYsImageUrl(vo.getDoctorImageUrl());
                entity.setFeature(vo.getFeature());
                entity.setOrderCount(vo.getOrderCount());
                entity.setYsId(vo.getDoctorId());
                entity.setYsMc(vo.getDoctorName());
                ysservice.insert(entity);
            }
        }

    }

    public List<YyDO> findList() {
        return yydao.findList();
    }

    public int insert(YyDO entity) {
        Assert.notNull(entity, "entity is null");
        return yydao.insert(entity);
    }

    public YyDO findById(int id) {
        YyDO yy = yydao.findById(id);
        return yy;
    }
}
