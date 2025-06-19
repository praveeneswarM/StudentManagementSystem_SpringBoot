package com.example.StudentManagement.controller;

import java.util.*;
import com.example.StudentManagement.Model.Student;
import com.example.StudentManagement.service.Student_Srvs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Student_ctrl {

    @Autowired
    Student_Srvs std;

    @PostMapping("/students/add")
    public ResponseEntity<String> addStudent(@Param("name") String name,@Param("rno") int rno,@Param("gender") String gender,@Param("tech") String tech)
    {
        String res=std.addStudent(name,rno,gender,tech);
        if(res==null)
            return new ResponseEntity<>("Student added Sussessfully!!", HttpStatus.CREATED);
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/students/update/{rno}")
    public ResponseEntity<String> updateStudent(@Param("name") String name, @PathVariable("rno") int rno, @Param("gender") String gender, @Param("tech") String tech)
    {
        String res=std.updateStudent(name,rno,gender,tech);
        if(res==null)
            return new ResponseEntity<>("Student updated Sussessfully!!", HttpStatus.OK);
        return new ResponseEntity<>(res,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/students/delete/{rno}")
    public ResponseEntity<String> deleteStudent(@PathVariable("rno") int rno)
    {
        String res=std.deleteStudent(rno);
        if(res==null)
            return new ResponseEntity<>("Student Deleted Sussessfully!!", HttpStatus.OK);
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents()
    {
        List<Student> list=std.getStudents();
        if(list==null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @GetMapping("/students/deleted")
    public ResponseEntity<List<Student>> getDeleted()
    {
        List<Student> list=std.getDeleted();
        if(list==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @GetMapping("/students/{rno}")
    public ResponseEntity<Student> getByRno(@PathVariable("rno") int rno)
    {
        Student stud=std.getByRno(rno);
        if(stud!=null)
            return new ResponseEntity<>(stud,HttpStatus.FOUND);
        return new ResponseEntity<>(new Student(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/students/filter")
    public ResponseEntity<List<Student>> getByGenTech(@Param("gender") String gender,@Param("tech") String tech)
    {
        List<Student> list=std.getByGenTech(gender,tech);
        if(list.isEmpty())
            return new ResponseEntity<>(new ArrayList<Student>(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @PutMapping("/students/status/{rno}")
    public ResponseEntity<String> setTrue(@PathVariable("rno") int rno)
    {
        String res=std.setStatus(rno);
        if(res==null)
            return new ResponseEntity<>("Student already exist",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Status set successfully",HttpStatus.OK);
    }
}
