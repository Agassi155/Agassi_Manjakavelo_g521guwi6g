package com.test.security.studentdao;

import com.test.security.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Student> getAllStudent(Pageable pageable) {
        String sql = "SELECT * FROM students ";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        int totalRecords = getTotalRecords(pageable, query);
        List<Student> students = query.getResultList();
        return new PageImpl<>(students, pageable, totalRecords);
    }

    public Page<Student> findByClassNameOrTeacherFullName(String className, String teacherFullName, Pageable pageable){
        String sql  = "SELECT s.id  ,s.first_name ,s.last_name,s.class_id\n" +
                "FROM students s\n" +
                "JOIN classes c ON s.class_id = c.id\n" +
                "JOIN teachers t ON t.class_id = c.id\n" +
                "WHERE c.name = :className OR CONCAT(t.first_name,t.last_name) = :teacherFullName";
        Query query = entityManager.createNativeQuery(sql,Student.class);
        query.setParameter("teacherFullName", teacherFullName);
        query.setParameter("className", className);
        int totalRecords = getTotalRecords(pageable, query);
        List<Student> students = query.getResultList();
        return new PageImpl<>(students, pageable, totalRecords);
    }


    //calculate total records
    private static int getTotalRecords(Pageable pageable, Query query) {
        int totalRecords = query.getResultList().size();
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        query.setFirstResult(offset);
        query.setMaxResults(pageable.getPageSize());
        return totalRecords;
    }
}
