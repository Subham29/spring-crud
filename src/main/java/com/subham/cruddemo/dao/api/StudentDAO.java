package com.subham.cruddemo.dao.api;

import com.subham.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findBYFirstName(String firstName);

    void update(Student student);

    void deleteStudent(Integer id);

    int deleteAllStudents();
}
