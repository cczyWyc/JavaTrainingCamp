package com.cczyWyc.task.task_02.xml.entity;

import java.util.List;

/**
 * class entity
 *
 * @author wangyc
 */
public class Klass {
    /** class id */
    private Long id;
    /** student list */
    private List<Student> studentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
