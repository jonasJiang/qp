package smb.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignUtil {
    private final static String md5sign = "mDf7QWXN09Wb0GIu871NkM0wYAKwBQ4Q";

    public static void main(String[] args) {
        try {

            //            clientIp=192.168.1.1&sign=0deb5365e720d00d57019abb03699d11&time=1496155687&organization=957100006&yhmm=5f4dcc3b5aa765d61d8327deb882cf99&idcard=MzMxMDgxMTk4NTA5MDExMjE1

            String a7 = "192.168.1.1MzMxMDgxMTk4NTA5MDExMjE195710000614961556875f4dcc3b5aa765d61d8327deb882cf99";
            System.out.println(MD5Support.MD5(a7));
            System.out.println(MD5Support.MD5(a7 + md5sign));

            sign(new HashMap());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String sign(HashMap localHashMap) throws Exception {
        localHashMap.put("yhmm", "5f4dcc3b5aa765d61d8327deb882cf99");
        localHashMap.put("idcard", "MzMxMDgxMTk4NTA5MDExMjE1");
        //        Map paramString2 = UrlConnectionUtil.sortMapByKey(localHashMap);
        //        String paramString1 = "";
        //        while (((Iterator) paramString2).hasNext())
        //        {
        //            Map.Entry localEntry = (Map.Entry)((Iterator)paramString2).next();
        //          paramString1 = paramString1 + (String)localEntry.getValue();
        //        }
        //        localHashMap.put("sign", Coder.encodeMd5(paramString1 + "mDf7QWXN09Wb0GIu871NkM0wYAKwBQ4Q"));
        System.out.println(getEncryptorParamsData(localHashMap));
        return "";
    }

    public static String getEncryptorParamsData(HashMap<String, String> paramMap) throws Exception {
        paramMap.put("organization", "957100002");
        paramMap.put("time", System.currentTimeMillis() / 1000L + "");
        paramMap.put("clientIp", "192.168.1.1");
        Map<String, String> sortmap = UrlConnectionUtil.sortMapByKey(paramMap);
        StringBuffer signkey = new StringBuffer();
        Iterator<?> it = sortmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            signkey.append((String) entry.getValue());

        }
        StringBuffer sortkey = new StringBuffer();
        paramMap.put("sign", MD5Support.MD5(signkey + md5sign));
            try {
                it = paramMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    sortkey.append(entry.getKey());
                    sortkey.append("=");
                    sortkey.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                    sortkey.append("&");
                }
                sortkey.deleteCharAt(sortkey.length() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return sortkey.toString();
    }
    
    public static HashMap<String, String> encryptorParamsData(HashMap<String, String> paramMap) throws Exception {
        paramMap.put("organization", "957100002");
        paramMap.put("time", System.currentTimeMillis() / 1000L + "");
        paramMap.put("clientIp", "192.168.1.1");
        Map<String, String> sortmap = UrlConnectionUtil.sortMapByKey(paramMap);
        StringBuffer sortkey = new StringBuffer();
        Iterator<?> it = sortmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sortkey.append((String) entry.getValue());

        }
        paramMap.put("sign", MD5Support.MD5(sortkey + md5sign));
          
        return paramMap;
    }
    
}
