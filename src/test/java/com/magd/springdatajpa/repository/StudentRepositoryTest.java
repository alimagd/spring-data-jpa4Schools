package com.magd.springdatajpa.repository;

import com.magd.springdatajpa.entity.Guardian;
import com.magd.springdatajpa.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("ali@mail.com")
                .firstName("Ali")
                .lastName("Majidi")
//                .guardian(guardian)
                .build();
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Heydar")
                .mobile("09192412315")
                .email("baba@mail.com")
                .build();

        Student student = Student.builder()
                .emailId("ali@mail.com")
                .firstName("Ali")
                .lastName("Majidi")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Hamid");
        System.out.println("students = " + students);
    }
    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("i");
        System.out.println("students = " + students);
    }
}