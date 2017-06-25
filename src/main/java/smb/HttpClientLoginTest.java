package smb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
 
public class HttpClientLoginTest {
    @Test
    // 获取一个HTML页面的内容，一个简单的get应用
    public void grabPageHTML() throws Exception {
        //创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpGet httpget = new HttpGet("https://www.baidu.com/");  
        System.out.println(httpget.getRequestLine());  
        //执行get请求  
        HttpResponse httpResponse = closeableHttpClient.execute(httpget);  
        //获取响应消息实体  
        HttpEntity entity = httpResponse.getEntity();  

        String html = EntityUtils.toString(entity, "UTF-8");
 
        // releaseConnection等同于reset，作用是重置request状态位，为下次使用做好准备。
        // 其实就是用一个HttpGet获取多个页面的情况下有效果；否则可以忽略此方法。
        httpget.releaseConnection();
        //关闭流并释放资源  
        closeableHttpClient.close();  
        System.out.println(html);
    }
 
    // 下载一个文件到本地（本示范中为一个验证码图片）
    @Test
    public void downloadFile() throws Exception {
        String url = "http://www.lashou.com/account/captcha";
        File dir = new File("D:\\TDDOWNLOAD");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String destfilename = "D:\\TDDOWNLOAD\\yz.png";

        //创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpGet httpget = new HttpGet(url);  

        
        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }
        //执行get请求  
        HttpResponse response = closeableHttpClient.execute(httpget);  
        //获取响应消息实体  
        HttpEntity entity = response.getEntity();  
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            // 在用InputStream处理HttpEntity时，切记要关闭低层流。
            in.close();
        }
        closeableHttpClient.close();  

        httpget.releaseConnection();
    }
 
    @Test
    // Post方法，模拟表单提交参数登录到网站。
    // 结合了上面两个方法：grabPageHTML/downloadFile，同时增加了Post的代码。
    public void login2Lashou() throws Exception {
        // 第一步：先下载验证码到本地
        saveImageToDisk();

        // 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入下载下来的验证码中显示的数字...");
        String yan = br.readLine();
        HttpPost httppost = new HttpPost("http://guahao.zjol.com.cn/logon");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("idcard", "330106196405232721"));
        params.add(new BasicNameValuePair("password", "5f4dcc3b5aa765d61d8327deb882cf99"));
        params.add(new BasicNameValuePair("verifyCode", "x474"));
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
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();
//        [version: 0][name: JSESSIONID] [value: 9C5DB7E0325A3C01955F83DCA84B4367]
//      cookie.getValue();

        testGetinfoByLoginCookie();
    }
    public static CookieStore setCookieStore(HttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        CookieStore cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
            .getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
            setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
            JSESSIONID);
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
    //把从服务器获得图片的输入流InputStream写到本地磁盘
    public static void saveImageToDisk() {

        InputStream inputStream = getInputStream();
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("/Users/jiang/Downloads/yzm.jpg");
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    // 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
    public static InputStream getInputStream() {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            String URL_PATH = "http://guahao.zjol.com.cn/validate_Code/login";
            URL url = new URL(URL_PATH);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();

            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inputStream;

    }

    @Test
    public void testSystemIn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String hello = reader.readLine();
        System.out.println(hello);
    }
 
    @Test
    // 设置已获取的cookie，查看需要登录后才能查看的页面
    public void testGetinfoByLoginCookie() throws Exception, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//         httpclient.addRequestInterceptor(new RequestAcceptEncoding());
//         httpclient.addResponseInterceptor(new ResponseContentEncoding());
//        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie1 = new BasicClientCookie("JSESSIONID","548B12A0B5EF8DD00E2305325E2C3AFD");
        cookie1.setDomain("guahao.zjol.com.cn");
        cookieStore.addCookie(cookie1);
        List<Cookie> cookies = new ArrayList<Cookie>();
        httpclient.setCookieStore(cookieStore);
        List<Cookie> cookieList = httpclient.getCookieStore().getCookies();
        for(int i=0;i<cookieList.size();i++){
            System.out.println("cookie"+i+":"+cookieList.get(i));
        }
         
        // 设置已登录的cookie
        String memberpage = "http://guahao.zjol.com.cn/pb/957102?deptId=17386&fuzzy_deptId=0&docId=&fuzzy_docId=0";
        HttpGet httpget = new HttpGet(memberpage);
        HttpResponse response = httpclient.execute(httpget); // 必须是同一个HttpClient！
        HttpEntity entity = response.getEntity();
        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();
 
        System.out.println(html);
    }
    
    @Test
    public void pb() throws ClientProtocolException, IOException{
        String url="http://guahao.zjol.com.cn/pb/957102?deptId=17386&fuzzy_deptId=0&docId=&fuzzy_docId=0";
        String sessionid="548B12A0B5EF8DD00E2305325E2C3AFD";
//        basehttp(url,sessionid);//排班
        
        url="http://guahao.zjol.com.cn/getHyInfo/957102?PBID=ws-957102-9327&YYPBID=ws-957102-9327&HYRQ=20170428&YYLX=0&DOCID=154&GHF=150&DEPTNAME=%25E6%25BB%25A8%25E6%25B1%259F%25E7%2594%25B2%25E7%258A%25B6%25E8%2585%25BA%25E5%25A4%2596%25E7%25A7%2591&DEPTHISCODE=17386";
        basehttp(url,sessionid);//号源
    }
    
    public void basehttp(String url,String sessionid) throws ClientProtocolException, IOException{
        DefaultHttpClient httpclient = new DefaultHttpClient();
      CookieStore cookieStore = new BasicCookieStore();
      BasicClientCookie cookie1 = new BasicClientCookie("JSESSIONID","548B12A0B5EF8DD00E2305325E2C3AFD");
      cookie1.setDomain("guahao.zjol.com.cn");
      cookieStore.addCookie(cookie1);
      List<Cookie> cookies = new ArrayList<Cookie>();
      httpclient.setCookieStore(cookieStore);
       
      // 设置已登录的cookie
      HttpGet httpget = new HttpGet(url);
      HttpResponse response = httpclient.execute(httpget); // 必须是同一个HttpClient！
      HttpEntity entity = response.getEntity();
      entity = response.getEntity();
      String html = EntityUtils.toString(entity, "GBK");
      httpget.releaseConnection();

      System.out.println(html);
    }
}
