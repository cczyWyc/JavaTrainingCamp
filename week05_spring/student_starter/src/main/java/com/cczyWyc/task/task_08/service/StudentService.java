package com.cczyWyc.task.task_08.service;

import com.cczyWyc.task.task_08.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangyc
 */
public class StudentService {
    private Student student;

    @Autowired
    public void setStudent(Student student) {
        this.student = student;
    }

    public String hello() {
        return "hello" + student.getName();
    }
}
