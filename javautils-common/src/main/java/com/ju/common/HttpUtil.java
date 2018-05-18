package com.ju.common;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24.
 */
public final class HttpUtil {

    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    private final static String contentType = "application/json";
    private final static String charset = "utf-8";
    private final static int connectTimeout = 3000;
    private final static int readTimeout = 15000;


    public static String sendGet(String url) throws Exception{
        if("".equals(url)){
            return null;
        }

        URL Obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)Obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");

        int responseCode = conn.getResponseCode();
        System.out.println("Sending 'Get' request to URL:"+url);
        System.out.println("Response Code:"+responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine=in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public final static String doPostForm(String url,Map<String,String> paramMap) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for(String key:paramMap.keySet()){
            stringBuffer.append(key).append("=").append(URLEncoder.encode(paramMap.get(key),"UTF-8")).append("&");
        }
        String paramStr = stringBuffer.toString().substring(0,stringBuffer.toString().lastIndexOf("&"));
        return doPost(url, paramStr,"application/x-www-form-urlencoded");
    }

    public final static String doPost(String url,String param)throws Exception{
        return doPost(url,param,contentType);
    }
    
    public final static String doPost(String url,String param,Integer connectTimeout,Integer readTimeout)throws Exception{
    	return doPost(url,param,contentType,connectTimeout,readTimeout);
    }

    public final static String doPost(String url,String param,String contentType)throws Exception{
        return doPost(url,param,contentType,connectTimeout,readTimeout);
    }

    public final static String doPost(String url,String param,String contentType,Integer connectTimeout,Integer readTimeout)throws Exception{
        return doPost(url,param,userAgent,contentType,connectTimeout,readTimeout);
    }

    public final static String doPost(String url,String param,String userAgent,String contentType,Integer connectTimeout,Integer readTimeout)throws Exception{
        return doPost(url,param,userAgent,contentType,charset,connectTimeout,readTimeout);
    }

    public final static String doPost(String url,String param,String userAgent,String contentType,String charset,Integer connectTimeout,Integer readTimeout) throws Exception{
        //判断参数是否为空
    	System.out.println("已进入HttpsUtil，开始执行方法");
        if(null==url||"".equals(url)){
            throw new RuntimeException("url为空");
        }
        if(null==param||"".equals(param)){
            throw new RuntimeException("param为空");
        }
        if(null==userAgent||"".equals(userAgent)){
            throw new RuntimeException("userAgent为空");
        }

        if(null==contentType||"".equals(contentType)){
            throw new RuntimeException("contentType为空");
        }

        if(null==charset||"".equals(charset)){
            throw new RuntimeException("charset为空");
        }

        HttpURLConnection conn = null;

        PrintWriter printWriter = null;
        BufferedReader br = null;

        String rsp = null;
        try{
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent",userAgent);
            conn.setRequestProperty("Content-Type",contentType);
            conn.setRequestProperty("charset",charset);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            printWriter = new PrintWriter(conn.getOutputStream());
            printWriter.print(param);
            printWriter.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode="+responseCode);
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine=br.readLine()) != null){
                response.append(inputLine);
            }
            br.close();
            rsp = response.toString();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(printWriter!=null){
                    printWriter.close();
                }
                if (br!=null){
                    br.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return rsp;
    }


}
