package com.blog.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.*;

public class HttpClientUtils  {
    public String httpPost(String url, Map<String, String> paramsMap) {  
        return httpPost(url, paramsMap, null);  
    }  
    /** 
     * 将返回结果转化为String 
     *  
     * @param entity 
     * @return 
     * @throws Exception 
     */  
    private String getRespString(HttpEntity entity) throws Exception {  
        if (entity == null) {  
            return null;  
        }  
        InputStream is = entity.getContent();  
        StringBuffer strBuf = new StringBuffer();  
        byte[] buffer = new byte[4096];  
        int r = 0;  
        while ((r = is.read(buffer)) > 0) {  
            strBuf.append(new String(buffer, 0, r, "UTF-8"));  
        }  
        return strBuf.toString();  
    }  
    /** 
     * http的post请求 
     *  
     * @param url 
     * @param paramsMap 
     * @return 
     */  
    public String httpPost(String url, Map<String, String> paramsMap,  
            Map<String, String> headMap) {  
        String responseContent = null;  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpPost httpPost = new HttpPost(url);  
            setPostHead(httpPost, headMap);  
            setPostParams(httpPost, paramsMap);  
            CloseableHttpResponse response = httpclient.execute(httpPost);  
            try {  
                System.out.println(response.getStatusLine());  
                HttpEntity entity = response.getEntity();  
                responseContent = getRespString(entity);  
                EntityUtils.consume(entity);  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println("responseContent = " + responseContent);  
        return responseContent;  
    }  
  
    /** 
     * 设置POST的参数 
     *  
     * @param httpPost 
     * @param paramsMap 
     * @throws Exception 
     */  
    private void setPostParams(HttpPost httpPost, Map<String, String> paramsMap)  
            throws Exception {  
        if (paramsMap != null && paramsMap.size() > 0) {  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            Set<String> keySet = paramsMap.keySet();  
            for (String key : keySet) {  
                nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
        }  
    }  
  
    /** 
     * 设置http的HEAD 
     *  
     * @param httpPost 
     * @param headMap 
     */  
    private void setPostHead(HttpPost httpPost, Map<String, String> headMap) {  
        if (headMap != null && headMap.size() > 0) {  
            Set<String> keySet = headMap.keySet();  
            for (String key : keySet) {  
                httpPost.addHeader(key, headMap.get(key));  
            }  
        }  
    }  
  
    /** 
     * 设置http的HEAD 
     *  
     * @param httpGet 
     * @param headMap 
     */  
    private void setGetHead(HttpGet httpGet, Map<String, String> headMap) {  
        if (headMap != null && headMap.size() > 0) {  
            Set<String> keySet = headMap.keySet();  
            for (String key : keySet) {  
                httpGet.addHeader(key, headMap.get(key));  
            }  
        }  
    }  
    /** 
     * 设置上传文件时所附带的其他参数 
     *  
     * @param multipartEntityBuilder 
     * @param params 
     */  
    private void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,  
            Map<String, String> params) {  
        if (params != null && params.size() > 0) {  
            Set<String> keys = params.keySet();  
            for (String key : keys) {  
                multipartEntityBuilder  
                        .addPart(key, new StringBody(params.get(key),  
                                ContentType.TEXT_PLAIN));  
            }  
        }  
    }  
    /** 
     * 上传文件 
     *  
     * @param serverUrl 
     *            服务器地址 
     * @param localFilePath 
     *            本地文件路径 
     * @param serverFieldName 
     * @param params 
     * @return 
     * @throws Exception 
     */  
    public String uploadFileImpl(String serverUrl, String localFilePath,  
            String serverFieldName, Map<String, String> params)  
            throws Exception {  
        String respStr = null;  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpPost httppost = new HttpPost(serverUrl);  
            FileBody binFileBody = new FileBody(new File(localFilePath));  
  
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder  
                    .create();  
            // add the file params  
            multipartEntityBuilder.addPart(serverFieldName, binFileBody);  
            // 设置上传的其他参数  
            setUploadParams(multipartEntityBuilder, params);  
  
            HttpEntity reqEntity = multipartEntityBuilder.build();  
            httppost.setEntity(reqEntity);  
  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                respStr = getRespString(resEntity);  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } finally {  
            httpclient.close();  
        }  
        System.out.println("resp=" + respStr);  
        return respStr;  
    }  
}
