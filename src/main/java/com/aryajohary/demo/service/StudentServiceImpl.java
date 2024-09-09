package com.aryajohary.demo.service;

import com.aryajohary.demo.dao.StudentDao;
import com.aryajohary.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        studentDao.delete(id);
    }
}
