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
                .emailId("Jafar@mail.com")
                .firstName("Jafar")
                .lastName("MaGD")
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

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Ali");
        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress(
                "ali@mail.com"
        );
        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentLastNameByEmailAddress() {
        String lastName = studentRepository.getStudentLastNameByEmailAddress(
                "ali@mail.com"
        );
        System.out.println("lastName = " + lastName);
    }

    @Test
    public void printGetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative(
                "ali@mail.com"
        );
        System.out.println("student = " + student);

    }
    @Test
    public void printGetStudentByEmailNativeNameParam() {
        Student student = studentRepository.getStudentByEmailNativeNamedParam(
                "ali@mail.com"
        );
        System.out.println("student = " + student);

    }

    @Test
    public void printUpdateStudentNameByEmailId() {
        studentRepository.updateStudentNameByEmailId(
                "AliJan", "ali@mail.com"
        );
        System.out.println("the firstname of the student with email:\"ali@mail.com\"\n changed");

    }
}