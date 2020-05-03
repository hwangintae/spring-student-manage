package com.intaehwang.springtanz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String userName;
    private int age;
    private int grade;

    @Enumerated(EnumType.STRING)
    private UserTypeName userType;

    private Address address;

    @OneToMany
    public List<Exam> exams;
}
