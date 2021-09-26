package com.cczyWyc.task.task_06;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * use okhttp send request
 *
 * @author wangyc
 */
public class OkHttp {
    public static void main(String[] args) {
        String url = "http://localhost:8801/";
        System.out.println(get(url));
    }

    private static String get (String uri) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(uri).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return responseBody.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
