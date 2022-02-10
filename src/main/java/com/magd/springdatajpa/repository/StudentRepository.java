package com.magd.springdatajpa.repository;

import com.magd.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    // to know more about how to create and add JPQL Query go to address :
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    //JPQL e.g.1
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);
    // e.g.2
    @Query("select s.lastName from Student s where s.emailId = ?1")
    String getStudentLastNameByEmailAddress(String emailId);

    // Native Query : go to mysql-workbench and see the native names of the tables and columns in db
    @Query(
            value = "select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    // Parameterize Query:
    @Query(
            value = "select * from tbl_student s where s.email_address =:emailId",
            nativeQuery = true
    )
    Student getStudentByEmailNativeNamedParam(@Param("emailId") String emailId);

    // Query updating
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name=?1 where email_address=?2 ",
            nativeQuery = true
    )
    void updateStudentNameByEmailId(String firstName, String emailId);









}
