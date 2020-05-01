package com.intaehwang.springtanz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Subject {

    @Id @GeneratedValue
    private Long id;

    private String year;
    private String semester;
    private String subjectName;
    private String teacherCode;

    @OneToMany
    private List<Exam> examList;
}
