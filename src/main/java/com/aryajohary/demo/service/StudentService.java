package com.aryajohary.demo.service;

import com.aryajohary.demo.entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> findAll();
    Student findById(Integer id);
    void delete(Integer id);
}
