package com.example.jpademo.repository;

import com.example.jpademo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

// @Transactional can be class level, or method level
//@Repository
//@Transactional
public class UserDaoService {

    // Not sure if it's used currently for CrudJpa
    // PersistenceContext manages object caching too.
    // User
    // jack.setRole("User");
    // this will make the object managed by PersistenceContext.
    @PersistenceContext
    private EntityManager em;

    public int insert(User user) {
        em.persist(user);
        return user.getId();
    }

    /**
     * Spring Data:
     *    It calls EntityManager to do CRUD, you don't need to manage them by youelf
     */
}
