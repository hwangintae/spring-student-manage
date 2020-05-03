package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Address;
import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.domain.UserTypeName;
import com.intaehwang.springtanz.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class MemberController {

    @Autowired
    MemberService memberService;

    // 회원가입
    @GetMapping("/members/new")
    public String createMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) return "members/createMemberForm";

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setUserName(form.getUserName());
        member.setAge(form.getAge());
        member.setGrade(form.getGrade());
        member.setAddress(address);
        member.setUserType(UserTypeName.STUDENT);

        memberService.join(member);
        return "redirect:/";
    }

    // 회원 조회 및 수정
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> findMember = memberService.findStudentList();
        model.addAttribute("members", findMember);

        return "members/memberList";
    }
    @GetMapping("member/{userId}/edit")
    public String updateMemberForm(@PathVariable("userId") String userId, Model model) {
        Member findMember = memberService.findOne(userId);

        MemberForm form = new MemberForm();
        form.setUserId(findMember.getUserId());
        form.setUserName(findMember.getUserName());
        form.setAge(findMember.getAge());
        form.setGrade(findMember.getGrade());
        form.setCity(findMember.getAddress().getCity());
        form.setStreet(findMember.getAddress().getStreet());
        form.setZipcode(findMember.getAddress().getZipcode());

        model.addAttribute("form", form);

        return "members/updateMemberForm";
    }
    @PostMapping(value = "/members/{userId}/edit")
    public String updateMember(@ModelAttribute("form") MemberForm form) {
        memberService.updateUser(form.getUserId(), form.getUserName(), form.getAge(), form.getGrade(), form.getCity(), form.getStreet(), form.getZipcode());
        return "redirect:/members";
    }

    // 교사 등록
    @GetMapping("/members/teacher/new")
    public String createTeacher(Model model) {
        List<Member> findMember = memberService.findStudentList();
        model.addAttribute("teacherForm", new MemberForm())
                .addAttribute("members", findMember);
        return "members/createTeacherForm";
    }
    @PostMapping(value = "/members/teacher/new/{userId}")
    public String updateUserTypeTeacher(@ModelAttribute("form") MemberForm form) {
        memberService.updateUserType(form.getUserId());
        return "redirect:/";
    }

    // 교사 조회 및 자격 박탈
    @GetMapping("/members/teacher")
    public String teacherList(Model model) {
        List<Member> findTeacher = memberService.findTeacherCodeList();
        model.addAttribute("members", findTeacher);

        return "members/teacherList";
    }

    @PostMapping("/members/teacher/{userId}/cancel")
    public String cancelTeacher(@PathVariable("userId") String userId) {
        memberService.updateUserType(userId);
        return "redirect:/members/teacherList";
    }
}
