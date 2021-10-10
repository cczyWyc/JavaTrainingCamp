package com.cczyWyc.task.task_03.gateway.router;

import java.util.List;

/**
 * httpclient request some backup server,use router to lb
 *
 * @author wangyc
 */
public interface HttpClientRouter {
    String route(List<String> urls);
}
