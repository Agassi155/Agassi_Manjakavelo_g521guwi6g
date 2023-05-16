package com.test.security.service;

import com.test.security.model.Student;
import com.test.security.studentdao.StudentDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    StudentDao daoStudent;

    public List<Student> getStudentList(String className,String teacherFullName, Pageable page) {
        if(className == null && teacherFullName == null){
            //getContent for students liste without having Page<Student>
            return daoStudent.getAllStudent(page).getContent();
        }
        //getContent for students liste without having Page<Student>
        return daoStudent.findByClassNameOrTeacherFullName(className,teacherFullName,page).getContent();
    }
}
