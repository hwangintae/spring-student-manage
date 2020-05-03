package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private String userId;
    private String userName;
    private int age;
    private int grade;

    private Address address;
    private String city;
    private String street;
    private String zipcode;
}
