package com.aryajohary.demo.dao;

import com.aryajohary.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    // creation of entity manager
    private EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery(
                "from Student"
        , Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }


    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }


}
