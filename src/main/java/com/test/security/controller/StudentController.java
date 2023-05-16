package com.test.security.controller;

import com.test.security.dto.StudentRequest;
import com.test.security.model.Student;
import com.test.security.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/list")
    public ResponseEntity<List<Student>> listStudent(@RequestBody StudentRequest request){
        Pageable page = PageRequest.of(request.getPage(),request.getSize());
        List<Student> studentPage = studentService.getStudentList(request.getClassName(), request.getFullName(), page);
        return ResponseEntity.ok(studentPage);
    }
}
