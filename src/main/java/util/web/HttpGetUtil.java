package util.web;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;


public class HttpGetUtil {

    @Test
    public void test_x() {
        String url = "http://localhost:8080/appoa/summaryReport/list?pageSize=2&&page=1";
        httpGet(url , null,577L);
    }

    public static String httpGet(String url , String param , Long uid) {
        if (StringUtils.isBlank(url)) {
            url = Http.dUrl;
        }
        if (StringUtils.isNotBlank(param)) {
            url = url+"?"+param;
        }
        return send(url , uid);
    }

    public static String send(String url , Long uid) {
        HttpGet httpGet = new HttpGet(url);
        if (uid != null) {
            httpGet.addHeader("apg-oa-uid" , uid.toString());
        }
        return Http.send(httpGet);
    }

}
