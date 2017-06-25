package smb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import smb.service.dao.YishengDao;
import smb.service.entity.YishengDO;
import smb.service.vo.zj.YishengListVOFromUrl;
import smb.service.vo.zj.YishengVO;
import smb.service.vo.zj.yuyue.HaoyuanDateList;
import smb.service.vo.zj.yuyue.HaoyuanInfo;
import smb.service.vo.zj.yuyue.HaoyuanVOFromUrl;
import smb.service.vo.zj.yuyue.JzhVOFromUrl;
import smb.service.vo.zj.yuyue.OrderVOFromUrl;
import smb.util.NetUtil;
import smb.util.SignUtil;

@Service
public class YuyueService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate         restTemplate;

    @Autowired
    private YishengDao   ysdao;

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://app.zjwlyy.cn//alipay/numberlist";
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
        paramMap.add("clientIp", "183.129.221.117");
        paramMap.add("hyrq", "20170608");
        paramMap.add("organization", "957100002");
        paramMap.add("pbid", "ws-957102-10383");
        paramMap.add("sign", "0933b78eddb997b4cd97c531c33630de");
        paramMap.add("time", "1496324918");
        paramMap.add("token", "3CF0B9B5CAED4591842D0DF80B0EC686");
        paramMap.add("yylx", "1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JzhVOFromUrl answer = restTemplate.postForObject(url, paramMap, JzhVOFromUrl.class);
        System.out.println(answer);
    }

    /**
     * 根据医生id，取医生的排班信息
     */
    public void getYishengPaibanFromUrl(String yishengId) {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("yishengId", "48421");
            String url = NetUtil.appurl_zj_zhy + "/doctor/schedule/{yishengId}?json=format";
            String jsonStr = restTemplate.getForObject(url, String.class, map);
            Gson gson = new Gson();
            System.out.println("Start Gson parse jsondata");
            HaoyuanVOFromUrl response = gson.fromJson(jsonStr, HaoyuanVOFromUrl.class);
            System.out.println("end Gson parse jsondata");
            if (response.getBody() != null && !response.getBody().getPblb().isEmpty()) {
                Iterator it = response.getBody().getPblb().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, ArrayList<HaoyuanDateList>> entry = (Map.Entry) it.next();
                    ArrayList<HaoyuanDateList> list = (ArrayList<HaoyuanDateList>) entry.getValue();
                    for (HaoyuanDateList hy : list) {
                        System.out.println(hy.getDate());
                        for (HaoyuanInfo info : hy.getInfo()) {
                            if (info.getSwh() != null && info.getSwh().getSwsyhy() > 0) {
                                if (!info.getSwh().getYypbid().equals("0")) {
                                    System.out.println("swh剩余:" + info.getSwh().getSwsyhy()
                                                       + ",排班ID:" + info.getSwh().getYypbid());
                                }
                            }
                            if (info.getXwh() != null && info.getXwh().getXwsyhy() > 0) {
                                if (!info.getXwh().getYypbid().equals("0")) {
                                    System.out.println("xwh剩余:" + info.getXwh().getXwsyhy()
                                                       + ",排班ID:" + info.getXwh().getYypbid());
                                }

                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 取就诊序号
     */
    public void chooseJzxhFromUrl(String hyrq, String pbid, String token, String yylx) {
        try {
            String url = NetUtil.appurl_zj_zhy+"/alipay/numberlist";
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
            paramMap.add("clientIp", "183.129.221.117");
            paramMap.add("hyrq", "20170608");
            paramMap.add("organization", "957100002");
            paramMap.add("pbid", "ws-957102-10383");
            paramMap.add("sign", "1a313032afed10c6184b9993a5cd5dad");
            paramMap.add("time", "1496324326");
            paramMap.add("token", "3B0FB39217BFFD1F3C8DAF8BBA9BB31D");
            paramMap.add("yylx", NetUtil.app_type_zj_zhy_android);
            JzhVOFromUrl answer = restTemplate.postForObject(url, paramMap, JzhVOFromUrl.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 就诊序号--确认,取验证码
     */
    public void jzxhConfirm(String token,String yyid,String hyid) {
        try {
            //GET //alipay/Platform_Code/order?token=70FB46725BD98F82606025A2467ED1EB&yyid=957102&hyid=10383_2_17&organization=957100002&time=1496326175&clientIp=183.129.221.117&sign=5cb6dc862a4ea558d1671726981a8a9d
            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("token", token);
            paramMap.put("yyid", yyid);//医院id
            paramMap.put("hyid", hyid);// 号源id，如：10383_2_17
            
            String param = SignUtil.getEncryptorParamsData(paramMap);
            String url = NetUtil.appurl_zj_zhy+"/alipay/Platform_Code/order?"+param;
            //TODO
            JzhVOFromUrl answer = restTemplate.getForObject(url,JzhVOFromUrl.class, paramMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     *  下单
     */
    public void submitOrderConfirm(OrderVO vo) {
        try {
            //http://app.zjwlyy.cn//alipay/order/submit   200 POST  
            String url = NetUtil.appurl_zj_zhy+"/alipay/order/submit";
            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("hyid", vo.getHyid());
            paramMap.put("hyrq", vo.getHyrq());
            paramMap.put("hysj", vo.getHysj());
            paramMap.put("hyxh", vo.getHyxh());
            paramMap.put("ksid", vo.getKsid());
            paramMap.put("sxwbz", vo.getSxwbz());
            paramMap.put("token", vo.getToken());
            paramMap.put("ysid", vo.getYsid());
            paramMap.put("yyhyid", vo.getYyhyid());
            paramMap.put("yyid", vo.getYyid());
            paramMap.put("yypbid", vo.getYypbid());
            paramMap.put("yzm", vo.getYzm());
            paramMap = SignUtil.encryptorParamsData(paramMap);
            MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<String, String>();

            OrderVOFromUrl answer = restTemplate.postForObject(url, requestMap, OrderVOFromUrl.class);
            logger.info("ok:{}",answer);
            logger.info("ok info:{}",vo.getMobile(),vo.getName(),vo.getIdcard());
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Transactional
    public void updateYishengInfo(YishengListVOFromUrl json, String yyId, String yyMc,
                                  String keshiId, String keshiMc) {
        if (json.getxResponseCode() == 200) {
            logger.info("begin yiyuan:{},{},keshi:{},{}", yyId, yyMc, keshiId, keshiMc);

            ysdao.deleteByYyIdKeshiId(yyId, keshiId);
            for (YishengVO vo : json.getBody().getDatalist()) {
                if (StringUtils.isBlank(vo.getDoctorName())) {
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
        ysdao.deleteByYyIdKeshiId(yyId, keshiId);
    }
}
