package com.example.StudentManagement.service;

import com.example.StudentManagement.Model.Student;
import com.example.StudentManagement.repo.Student_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Student_Srvs {

    @Autowired
    Student_Repo stdRpo;

    public String addStudent(String name, int rno, String gender, String tech) {
        String res=null;
        if(name.length()==0 || name.length()>40)
            return "Enter Valid Name (eg. Praveen,Arun) less or under 40 characters";
        else if(rno==0 || stdRpo.existsByRno(rno))
            return "Roll Number Already Exist..Try to Update Student";
        else if((!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")|| gender.length()==0))
            return "Enter Valid Gender type (eg. Male,Female)";
        else if(tech.length()==0)
            return "Enter Valid technology (eg. IT,CSE,ECE,EEE)";
        else
        {
             stdRpo.save(new Student(name, rno, gender, tech));
             return res;
        }
    }

    public String updateStudent(String name, int rno, String gender, String tech) {
        String res=null;
        if(stdRpo.existsByRno(rno))
        {
            if(name.length()==0 || name.length()>40)
                return "Enter Valid Name (eg. Praveen,Arun) less or under 40 characters";
            else if((!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")|| gender.length()==0))
                return "Enter Valid Gender type (eg. Male,Female)";
            else if(tech.length()==0)
                return "Enter Valid technology (eg. IT,CSE,ECE,EEE)";
            else
            {
                Student std= stdRpo.findByRno(rno);
                std.setName(name);
                std.setGender(gender);
                std.setTech(tech);
                stdRpo.save(std);
                return res;
            }
        }
        return "Roll number not exist..Try to add Student";
    }

    public String deleteStudent(int rno) {
        if(stdRpo.existsByRno(rno))
        {
           Student std= stdRpo.findByRno(rno);
           std.setStatus(1);
           stdRpo.save(std);
           return null;
        }
        return "Student not exist";
    }

    public List<Student> getStudents() {
        return stdRpo.findByStatus(0);
    }

    public List<Student> getDeleted() {
        return stdRpo.findByStatus(1);
    }

    public Student getByRno(int rno) {
        Student res= stdRpo.findByRno(rno);
        if(res==null)
            return null;
        if(res.getStatus()==0)
            return res;
        return null;
    }


    public List<Student> getByGenTech(String gender, String tech) {
        List<Student> list= stdRpo.findByGenderAndTech(gender,tech);
        if(list.isEmpty())
            return null;
        List<Student> res=new ArrayList<>();
        for(Student e:list)
        {
            if(e.getStatus()==0)
                res.add(e);
        }
        return res;
    }

    public String setStatus(int rno) {
        Student std=stdRpo.findByRno(rno);
        if(std!=null)
        {
            std.setStatus(0);
            stdRpo.save(std);
            return "OK";
        }
        return null;
    }
}
