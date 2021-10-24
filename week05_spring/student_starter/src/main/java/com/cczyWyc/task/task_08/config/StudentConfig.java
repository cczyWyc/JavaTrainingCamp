package com.cczyWyc.task.task_08.config;

import com.cczyWyc.task.task_08.entity.Student;
import com.cczyWyc.task.task_08.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyc
 */
@Configuration
@EnableConfigurationProperties(Student.class)
@ConditionalOnClass(StudentService.class)
@ConditionalOnProperty(prefix = "student.info", value = "enabled", matchIfMissing = true)
public class StudentConfig {
    @Autowired
    private Student student;

    @Bean
    @ConditionalOnMissingBean(StudentService.class)
    public StudentService studentService() {
        StudentService studentService = new StudentService();
        studentService.setStudent(student);
        return studentService;
    }
}
