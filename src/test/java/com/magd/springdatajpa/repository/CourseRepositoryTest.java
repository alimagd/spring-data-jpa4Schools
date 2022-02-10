package com.magd.springdatajpa.repository;

import com.magd.springdatajpa.entity.Course;
import com.magd.springdatajpa.entity.Student;
import com.magd.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {


    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {

        List<Course> courses = courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher= Teacher.builder()
                .firstName("Fati")
                .lastName("Faramarzi")
                .build();
        Course course1 = Course.builder()
                .title("Ashpazi")
                .credit(200)
                .teacher(teacher)
                .build();
        Course course2 = Course.builder()
                .title("Dancing")
                .credit(250)
                .teacher(teacher)
                .build();
        courseRepository.saveAll(List.of(course1, course2));
    }

    // pagination method to get data page by page testing
    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords)
                .getContent();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalElements();
        System.out.println("totalElements = " + totalElements);
        int totalPages = courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalPages();
        System.out.println("totalPages = " + totalPages);

        System.out.println("courses = " + courses);
        List<Course> courses1 = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        System.out.println("courses1 = " + courses1);
    }

    // get Sorted
    @Test
    public void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(0, 3, Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses
                = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);

        List<Course> courses1
                = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        System.out.println("courses1 = " + courses1);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords
                = PageRequest.of(0, 10);

        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "Java Basic",
                        firstPageTenRecords
                ).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Kamal")
                .lastName("Majidi")
                .build();
        Student student=Student.builder()
                .emailId("pp@mail.ir")
                .firstName("Payman")
                .lastName("Maj")
                .build();
        Student student1=Student.builder()
                .emailId("esh@mail.ir")
                .firstName("Pari")
                .lastName("Majidof")
                .build();
        Course course = Course.builder()
                .teacher(teacher)
                .credit(900)
                .title("Omran")
                .build();
        course.addStudents(student);
        course.addStudents(student1);
        courseRepository.save(course);
        System.out.println("course = " + course);
    }
}