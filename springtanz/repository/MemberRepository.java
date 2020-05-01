package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne (String id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUserName(String userName) {
        return em.createQuery("select m from Member m where m.userName = :userName", Member.class)
                .setParameter("userName", userName)
                .getResultList();
    }
}
