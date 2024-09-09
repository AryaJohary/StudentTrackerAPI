package com.aryajohary.demo.dao;

import com.aryajohary.demo.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentDao {
    Student save(Student student);
    List<Student> findAll();
    Student findById(Integer id);
    void delete(Integer id);
}
