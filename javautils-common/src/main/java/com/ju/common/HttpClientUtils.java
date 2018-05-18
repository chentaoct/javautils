package com.ju.common;

;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陈涛
 * @description: HttpClientTest
 * @Date 2018/5/17 15:23
 */
public class HttpClientUtils {
    public static final String UTF8 = "UTF-8";
    public static final int STATUS_CODE = 200;
    public static final int TIME_OUT = 10000;
    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    /**
     *
     * @author 陈涛
     * @Date:2018/5/17 15:49
     * @param
     * @param timeOut
     * @return
     */
    public static String sendHttpGet(String url, Map<String, String> param, Integer timeOut) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("url为空");
        }
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String responseValue = null;
        try {
            timeOut = timeOut == null ? timeOut : TIME_OUT;
            RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
            // 2. 创建HttpGet对象
            StringBuilder sb = new StringBuilder();
            String requestUrl = null;
            sb.append(url);
            if (param != null && param.size() > 0) {
                sb.append("?");
                for (String key : param.keySet()) {
                    sb.append(key).append("=").append(URLEncoder.encode(param.get(key), "UTF-8")).append("&");
                }
                requestUrl = sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
            } else {
                requestUrl = url;
            }
            HttpGet httpGet = new HttpGet(requestUrl);
            httpGet.setConfig(config);
            httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625Firefox/3.6.6 Greatwqs");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json");
            // 3. 执行GET请求
            response = httpClient.execute(httpGet);
            if (response != null) {
                responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (response.getStatusLine().getStatusCode() == HttpClientUtils.STATUS_CODE) {
                    return responseValue;
                } else {
                    log.error("返回异常" + responseValue);
                    throw new RuntimeException("调用服务异常");
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseValue;
    }

    public static String sendHttpPostForm(String url, Map<String, String> param, Integer timeOut) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("url为空");
        }
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String responseValue = null;
        try {
            timeOut = timeOut == null ? timeOut : TIME_OUT;
            RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
            // 2. 创建HttpGet对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(config);
            httpPost.setHeader("Accept-Charset", "utf-8");
            httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
                httpPost.setEntity(entity);
            }
            // 3. 执行post请求
            response = httpClient.execute(httpPost);
            if (response != null) {
                responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (response.getStatusLine().getStatusCode() == HttpClientUtils.STATUS_CODE) {
                    return responseValue;
                } else {
                    log.error("返回异常" + responseValue);
                    throw new RuntimeException("调用服务异常");
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseValue;
    }

    public static String sendHttpPostJson(String url, String json, Integer timeOut) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("url为空");
        }
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String responseValue = null;
        try {
            timeOut = timeOut == null ? timeOut : TIME_OUT;
            RequestConfig config = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
            // 2. 创建HttpGet对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(config);
            httpPost.setHeader("Accept-Charset", "utf-8");
            httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            if (null != json) { // 解决中文乱码问题
                StringEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
                // entity.setContentEncoding(UTF8);
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            // 3. 执行post请求
            response = httpClient.execute(httpPost);
            if (response != null) {
                responseValue = EntityUtils.toString(response.getEntity(), "UTF-8");
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpClientUtils.STATUS_CODE) {
                    return responseValue;
                } else {
                    log.error("返回异常" + responseValue);
                    throw new RuntimeException("请求异常"+statusCode);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseValue;
    }
}
