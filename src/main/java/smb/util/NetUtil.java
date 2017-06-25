package smb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class NetUtil {

    public static final String appurl_zj_zhy  = "http://app.zjwlyy.cn";//  浙江智慧医
    public static final String app_type_zj_zhy_android  = "0";//  浙江智慧医-android

    public static final String httpurl_zj_zhy = "http://guahao.zjol.com.cn";

    // Post方法，模拟表单提交参数登录到网站。
    public static String dopost(String url, HashMap<String, String> map) {
        String html = "";
        // 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
        HttpPost httppost = new HttpPost("http://guahao.zjol.com.cn/logon");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Iterator<Entry<String, String>> iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next(); //map.entry 同时取出键值对
            params.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        //        params.add(new BasicNameValuePair("idcard", "330106196405232721"));
        //        params.add(new BasicNameValuePair("password", "5f4dcc3b5aa765d61d8327deb882cf99"));
        //        params.add(new BasicNameValuePair("verifyCode", "x474"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));

            //创建HttpClientBuilder  
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            //HttpClient  
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            //执行get请求  
            HttpResponse response = closeableHttpClient.execute(httppost);

            HttpEntity entity = response.getEntity();
            // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
            String postResult = EntityUtils.toString(entity, "GBK");
            // 创建CookieStore实例
            //        static CookieStore cookieStore = null;
            String setCookie = response.getFirstHeader("Set-Cookie").getValue();
            CookieStore cookieStore = setCookieStore(response);
            // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。

            for (Cookie cookie : cookieStore.getCookies())
                System.out.println("cookie begin***\n" + cookie + "\n cookie end");
            httppost.releaseConnection();

            // 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
            String memberpage = "http://guahao.zjol.com.cn/pb/957102?deptId=17386&fuzzy_deptId=0&docId=&fuzzy_docId=0";
            HttpGet httpget = new HttpGet(memberpage);
            response = closeableHttpClient.execute(httpget); // 必须是同一个HttpClient！
            entity = response.getEntity();
            html = EntityUtils.toString(entity, "GBK");
            httpget.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
        //        [version: 0][name: JSESSIONID] [value: 9C5DB7E0325A3C01955F83DCA84B4367]
        //      cookie.getValue();
        //        testGetinfoByLoginCookie();
    }

    // get方法，模拟url提交到网站。
    public static String dogetToJson(String url, HashMap<String, String> map) {
        String postResult = "";
        HttpGet httpget = new HttpGet(url);
        url = url + "?";
        try {

            for (Iterator<Entry<String, String>> iter = map.entrySet().iterator(); iter
                .hasNext();) {
                Map.Entry entry = (Map.Entry) iter.next(); //map.entry 同时取出键值对
                url = url + (String) entry.getKey() + "=" + (String) entry.getValue() + "&";
            }
            url.substring(0, url.length() - 1);

            //创建HttpClientBuilder  
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            //HttpClient  
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

            //执行get请求  
            HttpResponse response = closeableHttpClient.execute(httpget);

            HttpEntity entity = response.getEntity();
            // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
            postResult = EntityUtils.toString(entity, "GBK");
            // 创建CookieStore实例
            //        static CookieStore cookieStore = null;
            String setCookie = response.getFirstHeader("Set-Cookie").getValue();
            CookieStore cookieStore = setCookieStore(response);
            // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
            for (Cookie cookie : cookieStore.getCookies())
                System.out.println("cookie begin***\n" + cookie + "\n cookie end");
            httpget.releaseConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return postResult;
        //        [version: 0][name: JSESSIONID] [value: 9C5DB7E0325A3C01955F83DCA84B4367]
        //      cookie.getValue();
        //        testGetinfoByLoginCookie();
    }

    public static CookieStore setCookieStore(HttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        CookieStore cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/CwlProClient");
        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }
}
