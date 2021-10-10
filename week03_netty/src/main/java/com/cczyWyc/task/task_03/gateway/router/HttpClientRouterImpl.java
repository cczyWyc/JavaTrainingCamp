package com.cczyWyc.task.task_03.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * http client router implement。usr random to return a url
 *
 * @author wangyc
 */
public class HttpClientRouterImpl implements HttpClientRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
