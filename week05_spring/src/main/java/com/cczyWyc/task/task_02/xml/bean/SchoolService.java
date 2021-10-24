package com.cczyWyc.task.task_02.xml.bean;

/**
 * school service. use xml set bean
 *
 * @author wangyc
 */
public class SchoolService {
    public SchoolService() {
        System.out.println("This is a school service");
    }

    public void beanInformation() {
        System.out.println("This bean has been injected, now you can use it!");
    }
}
