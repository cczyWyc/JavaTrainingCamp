package com.cczywyc.springboot_orm_hikari.task_08.test;

import com.cczyWyc.task.task_08.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangyc
 */
@SpringBootTest
public class StudentStarterTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void test() {
        System.out.println(studentService.hello());
    }
}
