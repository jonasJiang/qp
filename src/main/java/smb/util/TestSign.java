package smb.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestSign {
    public static void main(String[] args) throws IOException {
        Map map = new HashMap();
        map.put("TOKEN", "18A8BE290158694E4780612CA69E98F9");
        map.put("clientIp", "183.129.221.117");
        map.put("organization", "957100002");
        map.put("phonenumval", "13588477968");
//        time=1495959836
        String md5sign ="mDf7QWXN09Wb0GIu871NkM0wYAKwBQ4Q";
      
        buildSign(map, md5sign);

    }

    public static String buildSign(Map params, String secret) {
//        Map treeMap = new TreeMap(params);// treeMap默认会以key值升序排序
        StringBuilder sb = new StringBuilder();
        Map treeMap =UrlConnectionUtil.sortMapByKey(params);
        Iterator it = treeMap.entrySet().iterator();
        while(it.hasNext()){
//            (Map.Entry)it.next();
//          sb.append(key).append("=").append(treeMap.get(key));
//            TOKEN=18A8BE290158694E4780612CA69E98F9&clientIp=183.129.221.117&organization=957100002&phonenumval=13588477968
        }
//        for (String key : treeMap.keySet().iterator()) {// 排序后的字典，将所有参数按"key=value"格式拼接在一起
//            sb.append(key).append("=").append(treeMap.get(key));
//        }
        sb.append("");
        sb.append(secret);
        System.out.println("待加密的源参数串为:" + sb.toString());
        MessageDigest md5;
        byte[] bytes = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(sb.toString().getBytes("UTF-8"));// md5加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        System.out.println("加密后签名为:" + sign.toString());
        return sign.toString();
    }
}