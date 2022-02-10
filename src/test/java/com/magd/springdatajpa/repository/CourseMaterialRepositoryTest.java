package com.magd.springdatajpa.repository;

import com.magd.springdatajpa.entity.Course;
import com.magd.springdatajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseMaterialRepositoryTest {


    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title("Java Basic")
                .credit(250)
                .build();
        CourseMaterial courseMaterial1 = CourseMaterial.builder()
                .course(course)
                .url("www.ali.com")
                .build();

        courseMaterialRepository.save(courseMaterial1);
    }

    @Test
    public void printAllCourseMaterials() {

        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);
    }
}