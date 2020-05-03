package com.intaehwang.springtanz.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SubjectRepository {

    @PersistenceContext
    private EntityManager em;


}
