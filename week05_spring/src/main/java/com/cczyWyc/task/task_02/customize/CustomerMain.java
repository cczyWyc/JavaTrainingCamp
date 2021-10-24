package com.cczyWyc.task.task_02.customize;

import com.cczyWyc.task.task_02.customize.bean.SchoolService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * customize set bean
 *
 * @author wangyc
 */
public class CustomerMain {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SchoolService.class);

        beanFactory.registerBeanDefinition("schoolService", beanDefinition);
        SchoolService schoolService = beanFactory.getBean(SchoolService.class);
        schoolService.beanInformation();
    }
}
