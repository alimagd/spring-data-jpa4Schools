package com.magd.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.concurrent.CountDownLatch;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") // by this it can be printed in console without course field
public class CourseMaterial {


    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false) // it's not optional means having course is mandatory
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course; // we have Bidirectional OneToOne mapping between Course & CourseMaterial

}
