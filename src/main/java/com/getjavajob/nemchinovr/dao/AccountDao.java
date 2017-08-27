package com.getjavajob.nemchinovr.dao;

import com.getjavajob.nemchinovr.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class AccountDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Account getById(int id) {
        return getAccountByFieldName("id", id);
    }

    public Account getByLogin(String login) {
        return getAccountByFieldName("login", login);
    }

    public Account getAccountByFieldName(String field, Object o) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
            Root<Account> root = criteriaQuery.from(Account.class);
            criteriaQuery.select(root).where(builder.equal(root.get(field), o));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void saveAccount(Account account) {
        entityManager.merge(account);
        entityManager.flush();
    }

    @Transactional
    public void createAccount(Account account) {
        entityManager.persist(account);
    }
}