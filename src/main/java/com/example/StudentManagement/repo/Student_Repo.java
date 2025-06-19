package com.example.StudentManagement.repo;

import com.example.StudentManagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_Repo extends JpaRepository<Student,Integer> {

    boolean existsByRno(int rno);

    Student findByRno(int rno);

    List<Student> findByStatus(int status);

    List<Student> findByGenderAndTech(String gender, String tech);
}
