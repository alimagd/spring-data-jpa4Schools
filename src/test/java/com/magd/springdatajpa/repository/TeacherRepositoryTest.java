package com.magd.springdatajpa.repository;

import com.magd.springdatajpa.entity.Course;
import com.magd.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course course1 = Course.builder()
                .title("Java Basic")
                .credit(250)
                .build();
        Course course2 = Course.builder()
                .title("Springboot")
                .credit(500)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("AliJan")
                .lastName("Majidi")
//                .courses(List.of(course1,course2))
                .build();
        teacherRepository.save(teacher);
    }

}