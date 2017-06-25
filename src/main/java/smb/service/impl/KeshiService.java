package smb.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import smb.service.dao.KeshiDao;
import smb.service.entity.KeshiDO;
import smb.service.vo.zj.Keshi;
import smb.service.vo.zj.KeshiBigType;
import smb.service.vo.zj.KeshiVOFromUrl;
import smb.util.NetUtil;

@Service
public class KeshiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  

    @Autowired
    RestTemplate           restTemplate;

    @Autowired
    private KeshiDao     ksdao;

    public int insert(KeshiDO entity) {
        Assert.notNull(entity, "entity is null");
        return ksdao.insert(entity);
    }

    public List<KeshiDO> findList() {
        return ksdao.findList();
    }
    public KeshiDO findById(int id) {
        return ksdao.findById(id);
    }

    public void deleteByYyid(String yyid) {
        ksdao.deleteByYyid(yyid);
    }

    /**
     * 取科室list，入库
     * @param yyid
     */
    public void getKeshilistFromUrl(String yyid, String yymc) {
        logger.info("begin yiyuan:{},{}",yyid,yymc);
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("yyid", yyid);
            //        restTemplate.getForEntity(url, responseType, uriVariables)
            String url = NetUtil.appurl_zj_zhy + "/hospital/department/" + yyid + "?json=format";
            KeshiVOFromUrl json = restTemplate.getForObject(url, KeshiVOFromUrl.class);
            if (json.getxResponseCode() == 200) {
                updateKeshiInfo(yyid, yymc, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateKeshiInfo(String yyid, String yymc, KeshiVOFromUrl json) {
        deleteByYyid(yyid);
        for (KeshiBigType bigtype : json.getBody().getDepts()) {
            for (Keshi vo : bigtype.getChilds()) {
                KeshiDO entity = new KeshiDO();
                entity.setKeshiId(vo.getDeptId());
                entity.setKeshiMc(vo.getDeptName());
                entity.setYyId(yyid);
                entity.setYyMc(yymc);
                insert(entity);
            }

        }

    }
}
