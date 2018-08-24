package util.mission;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;
import util.DbSource;

import java.util.ArrayList;
import java.util.List;

public class CreateMission {

    static Long[] mids = {};
    static Integer[] months = {1 , 2};

    public static void main(String[] args) {
        DbSource.init();
        try {
            run2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run2() throws Exception{
        String url = "http://stg2.v5time.net/apgoa/report/detail";
        List<Record> list = Db.find("select id from oa_finance_report where id not in(2332,2331)");
        try {
            for (Record record : list) {
                Long id = record.getLong("id");
                if (id != null) {
                    String result = sendParm(url,"reportId="+id);
                    Thread.currentThread().sleep(5000);
                    System.err.printf("id:%d,rel:%s\n" , id,result != null ? result : "失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void run(Long mids , Integer month) throws Exception {
        String url = "http://stg2.v5time.net/apgoa/timing/jobs";
        List<BasicNameValuePair> content = new ArrayList<BasicNameValuePair>();
        content.add(new BasicNameValuePair("mids" , mids.toString()));
        content.add(new BasicNameValuePair("month" , month.toString()));
        content.add(new BasicNameValuePair("year" , "2018"));
        //调用Http请求方法
        String result = sentHttpPostRequest(url , content);
        System.err.printf("rel:%s\n" , result);

    }

    public static String  sendParm(String url,String param) throws Exception{
        List<BasicNameValuePair> content = new ArrayList<BasicNameValuePair>();
        String[] arr2 = param.split("=");
        content.add(new BasicNameValuePair(arr2[0], arr2[1]));
        return sentHttpPostRequest(url , content);
    }

    public static String sentHttpPostRequest(String url , List<BasicNameValuePair> content) throws Exception {
        //构建HttpClient实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .build();
        //指定POST请求
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);

        //包装请求体
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.addAll(content);
        HttpEntity request = new UrlEncodedFormEntity(params , "UTF-8");

        //发送请求
        httppost.setEntity(request);
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);

        //读取响应
        HttpEntity entity = httpResponse.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity , "UTF-8");
        }
        return result;
    }


}
