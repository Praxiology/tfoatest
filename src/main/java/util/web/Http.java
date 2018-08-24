package util.web;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Http {

    public static String dUrl = "http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

    public static String send(HttpRequestBase base) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        try {
            CloseableHttpResponse httpResponse = httpclient.execute(base);
            //读取响应
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity , "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != result) {
            System.err.printf("result:[%s]\n" , result);
        }
        return result;
    }

}
