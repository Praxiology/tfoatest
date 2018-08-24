package util.cookie;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class Cookie {

    static String loginUrl ="http://stg2.v5time.net/apgoa/login";
    static CookieStore cookieStore = null;

    public static void main(String[] args) throws Exception {
        run();
    }
    static HttpPost request;
    static HttpContext context;
    public static void run() throws Exception {
        RequestAddCookies requestAddCookies = new RequestAddCookies();
        request = new HttpPost(loginUrl);
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"); // 设置请求头消息User-Agent
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("vcode" , "vcode"));
        nvps.add(new BasicNameValuePair("account" , "cw_report_jt_h"));
        nvps.add(new BasicNameValuePair("password" , "111111"));
        HttpEntity entityReq = new UrlEncodedFormEntity(nvps,"utf-8");
        request.setEntity(entityReq);
        context = HttpClientContext.create();
        context.setAttribute("vcode" , "vcode");
        context.setAttribute("account" , "cw_report_jt_h");
        context.setAttribute("password" , "111111");
        requestAddCookies.process(request,context);
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
        CloseableHttpResponse response = httpClient.execute(request,context);
        HttpEntity entity=response.getEntity(); // 获取返回实体
        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
        /*CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpPost http=new HttpPost(loginUrl); // 创建httpget实例
        http.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"); // 设置请求头消息User-Agent
        List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("vcode" , "vcode"));
        nvps.add(new BasicNameValuePair("account" , "cw_report_jt_h"));
        nvps.add(new BasicNameValuePair("password" , "111111"));
        HttpEntity entityReq2 = new UrlEncodedFormEntity(nvps,"utf-8");
        http.setEntity(entityReq);
        CloseableHttpResponse response=httpClient.execute(http); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭*/
    }
}
