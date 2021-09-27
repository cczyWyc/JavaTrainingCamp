package com.cczyWyc.task.task_06;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * use httpclient send request
 *
 * @author wangyc
 */
public class HttpClientDemo {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801/";
        System.out.println(get(url));
    }

    /**
     * use httpclient send get request to httpserver01
     *
     * @param uri url
     * @return response data
     * @throws IOException exception
     */
    private static String get(String uri) throws IOException {
        //create httpclient object
        String data = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                data = EntityUtils.toString(entity, Consts.UTF_8);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
