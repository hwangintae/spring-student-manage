package com.intaehwang.springtanz.service;

import com.intaehwang.springtanz.domain.Address;
import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findOne(member.getUserId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 ID 입니다.");
        }
    }

    // 회원 조회
    public Member findOne(String userId) {
        return memberRepository.findOne(userId);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public List<Member> findByUserName(String userName) {
        return memberRepository.findByUserName(userName);
    }


    // 회원 수정
    @Transactional
    public void updateUser(String userId, String newName, int age, int grade, String city, String street, String zipcode) {

        log.info("update user");

        Member member = memberRepository.findOne(userId);
        member.setUserName(newName);
        member.setAge(age);
        member.setGrade(grade);
        Address address = new Address(city, street, zipcode);
        member.setAddress(address);
    }

}
