package util.web;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpPostUtil {

    @Test
    public void test_x() {
        //String url = "http://localhost:8080/oa/finance/unit/query";
        sentHttpRequest(null , "");
    }

    public static String sentHttpRequest(String url , String param) {
        Map<String, String> _param = null;
        if (StringUtils.isNotBlank(param)) {
            _param = new HashMap<>();
            String[] arr1 = param.split(",");
            for (String s : arr1) {
                if (s != null) {
                    String[] arr2 = s.split("=");
                    _param.put(arr2[0] , arr2[1]);
                }
            }
        }
        return sentHttpRequest(url , _param);
    }

    public static String sentHttpRequest(String url , Map<String, String> param) {
        if (StringUtils.isBlank(url)) {
            url = Http.dUrl;
        }
        if (param == null) {
            param = new HashMap<>();
        }
        return send(url , param);

    }

    private static String send(String url , Map<String, String> param) {
        //指定POST请求
        HttpPost httppost = new HttpPost(url);
        //包装请求体
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        param.forEach((k , v) -> {
            if ("uid".equals(k)) {
                httppost.addHeader(new BasicHeader("apg-oa-uid" , v));
            }
            params.add(new BasicNameValuePair(k , v));
        });
        HttpEntity request = null;
        try {
            request = new UrlEncodedFormEntity(params , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //发送请求
        httppost.setEntity(request);
        //
        String info1 = httppost.toString();
        String info2 = JSON.toJSONString(param);
        System.err.printf("req_param:[%s]\n" , info1+"|"+info2);
        return Http.send(httppost);
    }

}
