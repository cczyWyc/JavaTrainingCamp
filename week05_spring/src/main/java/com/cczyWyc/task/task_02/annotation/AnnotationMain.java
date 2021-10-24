package com.cczyWyc.task.task_02.annotation;

import com.cczyWyc.task.task_02.annotation.bean.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author wangyc
 */

public class AnnotationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.cczyWyc.task.task_02.annotation.bean");
        SchoolService schoolService = context.getBean(SchoolService.class);
        schoolService.beanInformation();
    }
}
