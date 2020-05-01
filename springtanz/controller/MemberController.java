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

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> findMember = memberService.findAll();
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
    public String updateItem(@ModelAttribute("form") MemberForm form) {
        memberService.updateUser(form.getUserId(), form.getUserName(), form.getAge(), form.getGrade(), form.getCity(), form.getStreet(), form.getZipcode());
        return "redirect:/members";
    }
}
