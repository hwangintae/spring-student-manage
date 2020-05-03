package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class SubjectController {

    @Autowired
    MemberService memberService;
    @GetMapping("/subject/new")
    public String createSubject(Model model) {
        List<Member> teacherCodeList = memberService.findTeacherCodeList();
        model.addAttribute("subjectForm", new SubjectForm())
        return "subject/createSubjectForm";
    }
}
