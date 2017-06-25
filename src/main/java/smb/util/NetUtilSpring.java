package smb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import smb.ctl.DownloadInterceptor;

@Component
public class NetUtilSpring {

    public static final String appurl_zj_zhy  = "http://app.zjwlyy.cn";//  浙江智慧医
    public static final String app_type_zj_zhy_android  = "0";//  浙江智慧医-android

    public static final String httpurl_zj_zhy = "http://guahao.zjol.com.cn";

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 下载通用方法
     * @param url 地址
     * @param method 方法
     * @param params 参数
     * @return 下载流
     */
    protected ResponseEntity<byte[]> export(String url, HttpMethod method, Map<String, Object> params) {
        ClientHttpRequestInterceptor interceptor = new DownloadInterceptor();
        List<ClientHttpRequestInterceptor> list = new ArrayList<>();
        list.add(interceptor);

        restTemplate.setInterceptors(list);

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            if (params != null) {
                str = mapper.writeValueAsString(params);
            }
        } catch (JsonProcessingException e) { // 这个异常不处理
            e.printStackTrace();
        }

        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(str, headers);
        ResponseEntity<byte[]> obj = restTemplate.exchange(url, method, entity, byte[].class);

        return obj;

    }
}
