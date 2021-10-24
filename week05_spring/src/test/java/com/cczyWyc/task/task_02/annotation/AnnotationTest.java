package com.cczyWyc.task.task_02.annotation;

import com.cczyWyc.task.task_02.annotation.bean.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author wangyc
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SchoolService.class)
public class AnnotationTest {
    @Autowired
    private SchoolService schoolService;

    @Test
    public void test() {
        schoolService.beanInformation();
    }
}
