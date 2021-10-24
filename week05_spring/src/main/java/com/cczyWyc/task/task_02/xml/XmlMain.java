package com.cczyWyc.task.task_02.xml;

import com.cczyWyc.task.task_02.xml.bean.SchoolService;
import com.cczyWyc.task.task_02.xml.entity.Klass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangyc
 */
public class XmlMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent-xml.xml");

        //get value
        Klass bean = context.getBean(Klass.class);
        System.out.println(bean.getStudentList().get(0).getName());

        //get object
        SchoolService schoolService = context.getBean(SchoolService.class);
        schoolService.beanInformation();
    }
}
