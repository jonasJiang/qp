package smb.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class UrlConnectionUtil {
    private static final int    CONNECT_TIME_OUT        = 30000;
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final String HEADER_CONTENT_LENGTH   = "Content-Length";
    private static final String LOG_TAG                 = "Http->JDK";
    private static final int    READ_TIME_OUT           = 15000;

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {  
            //降序排序  
            @Override  
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {  
                return o1.getValue().compareTo(o2.getValue());  
//                return o2.getValue().compareTo(o1.getValue());  
            }  
        });  
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        for(Map.Entry<String, String> entry :list){
            sortMap.put(entry.getKey(), entry.getValue());
        }
        return sortMap;
    }
}

class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
//
//public static String performPostRequest(String paramString, Map<String, String> paramMap, boolean paramBoolean)
//{
//    Object localObject4 = null;
//  Object localObject5 = null;
//  Object localObject6 = null;
//  Object localObject7 = null;
//  Object localObject8 = null;
//  Object localObject9 = null;
//  Object localObject2 = localObject6;
//  Object localObject3 = localObject7;
//  Object localObject1 = localObject8;
//  for (;;)
//  {
//    try
//    {
//      URL localURL = new URL(paramString);
//      paramString = (String)localObject9;
//      localObject1 = localObject5;
//      if (localURL != null)
//      {
//        localObject2 = localObject6;
//        localObject3 = localObject7;
//        localObject1 = localObject8;
//        paramString = (HttpURLConnection)localURL.openConnection();
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setConnectTimeout(30000);
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setReadTimeout(15000);
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setRequestMethod("POST");
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setRequestProperty("connection", "keep-alive");
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setRequestProperty("Charsert", "UTF-8");
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setDoInput(true);
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramString.setDoOutput(true);
//        if (!paramBoolean) {
//          continue;
//        }
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramMap = getEncryptorParamsData(paramMap);
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        if (Build.VERSION.SDK != null)
//        {
//          localObject2 = paramString;
//          localObject3 = paramString;
//          localObject1 = paramString;
//          if (Build.VERSION.SDK_INT > 13)
//          {
//            localObject2 = paramString;
//            localObject3 = paramString;
//            localObject1 = paramString;
//            paramString.setRequestProperty("Connection", "close");
//          }
//        }
//        if (paramMap != null)
//        {
//          localObject2 = paramString;
//          localObject3 = paramString;
//          localObject1 = paramString;
//          paramString.setFixedLengthStreamingMode(paramMap.length);
//          localObject2 = paramString;
//          localObject3 = paramString;
//          localObject1 = paramString;
//          paramString.setRequestProperty("Content-Length", String.valueOf(paramMap.length));
//          localObject2 = paramString;
//          localObject3 = paramString;
//          localObject1 = paramString;
//          paramString.getOutputStream().write(paramMap);
//        }
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        if (200 != paramString.getResponseCode()) {
//          continue;
//        }
//        localObject2 = paramString;
//        localObject3 = paramString;
//        localObject1 = paramString;
//        paramMap = getResultString(paramString.getInputStream(), getParamsEncoding());
//        localObject1 = paramMap;
//      }
//      paramMap = (Map<String, String>)localObject1;
//      if (paramString != null)
//      {
//        paramString.disconnect();
//        paramMap = (Map<String, String>)localObject1;
//      }
//    }
//    catch (MalformedURLException paramString)
//    {
//      localObject1 = localObject2;
//      paramString.printStackTrace();
//      paramMap = (Map<String, String>)localObject4;
//      return null;
//    }
//    catch (Exception paramString)
//    {
//      localObject1 = localObject3;
//      paramString.printStackTrace();
//      paramMap = (Map<String, String>)localObject4;
//      return null;
//    }
//    finally
//    {
//      if (localObject1 == null) {
//        continue;
//      }
//      ((HttpURLConnection)localObject1).disconnect();
//    }
//    return paramMap;
//    localObject2 = paramString;
//    localObject3 = paramString;
//    localObject1 = paramString;
//    paramMap = getParamsData(paramMap);
//    continue;
//    localObject2 = paramString;
//    localObject3 = paramString;
//    localObject1 = paramString;
//    Log.e("Http->JDK", "Connection failed: " + paramString.getResponseCode());
//    localObject1 = localObject5;
//  }
//}


}
