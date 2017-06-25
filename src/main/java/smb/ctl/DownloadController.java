package smb.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Heweipo on 2016/5/26.
 *
 * 资源下载通用控制器
 */
@RestController
public class DownloadController  {
@Autowired
    private RestTemplate restTemplate;

    /**
     * 发送/获取 服务端数据
     * 注意 URL 、HttpMethod 都与当前请求保持一致
     *
     * @return 返回结果(响应体)
     */
//    protected ResponseEntity<byte[]> export() {
//        return export(null, null);
//    }

    /**
     * 主要是 GET、DELETE 请求不能发在body体
     *
     * @param uri 相对路径
     * @return 返回结果(响应体)
     */
//    protected ResponseEntity<byte[]> export(String uri) {
//        return export(uri, null);
//    }

    /**
     * 添加参数
     *
     * @param params 请求body体
     * @return 返回结果(响应体)
     */
//    protected ResponseEntity<byte[]> export(Map<String, Object> params) {
//        return export(null, params);
//    }

    /**
     * 下载通用方法
     *
     * @param params 请求参数
     * @return 下载流
     */
//    protected ResponseEntity<byte[]> export(String uri, Map<String, Object> params) {
//        // RestClient.getServiceServerUrl() 服务器的URL ： http://localhost:8080/service
//        String url = RestClient.getServiceServerUrl() + request.getRequestURI();
//        if (!StringUtils.isEmpty(uri)) {
//            url = RestClient.getServiceServerUrl() + uri;
//        }
//
//        HttpMethod httpMethod = getMethod();
//        if(params == null) params = new HashMap<>();
//        url = processParams(url,httpMethod,params);
//
//        return export(url, httpMethod, params);
//    }



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


    /**
     * Spring RestTemplate 下载组件
     */
    public static class DownloadInterceptor implements ClientHttpRequestInterceptor{

        /**
          * 媒体类型,给个默认值
         */
        private MediaType headerValue = MediaType.ALL;

        /**
         * 获取媒体类型
         * @return 媒体类型
         */
        public MediaType getHeaderValue() {
            return headerValue;
        }

        /**
         * 设置媒体类型
         * @param headerValue 媒体类型
         */
        public void setHeaderValue(MediaType headerValue) {
            this.headerValue = headerValue;
        }


     /**
      * Intercept the given request, and return a response. The given {@link ClientHttpRequestExecution} allows
      * the interceptor to pass on the request and response to the next entity in the chain.
      * <p>
      * <p>A typical implementation of this method would follow the following pattern:
      * <ol>
      * <li>Examine the {@linkplain HttpRequest request} and body</li>
      * <li>Optionally {@linkplain HttpRequestWrapper wrap} the request to filter HTTP attributes.</li>
      * <li>Optionally modify the body of the request.</li>
      * <li><strong>Either</strong>
      * <ul>
      * <li>execute the request using {@link ClientHttpRequestExecution#execute(HttpRequest, byte[])},</li>
      * <strong>or</strong>
      * <li>do not execute the request to block the execution altogether.</li>
      * </ul>
      * <li>Optionally wrap the response to filter HTTP attributes.</li>
      * </ol>
      *
      * @param request   the request, containing method, URI, and headers
      * @param body      the body of the request
      * @param execution the request execution
      * @return the response
      * @throws IOException in case of I/O errors
      */
     @Override
     public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
         HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
         List<MediaType> list = new ArrayList<>();
         list.add(headerValue);
         requestWrapper.getHeaders().setAccept(list);
         return execution.execute(requestWrapper, body);
     }
 }



}