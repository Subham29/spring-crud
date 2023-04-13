package com.subham.cruddemo.dao.impl;

import com.subham.cruddemo.dao.api.StudentDAO;
import com.subham.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student order by lastName", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findBYFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where firstName=:theData order by lastName", Student.class);
        query.setParameter("theData", firstName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            System.out.println("Student does not exist!");
            return;
        }
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAllStudents() {
        return entityManager.createQuery("delete from Student").executeUpdate();
    }


}
