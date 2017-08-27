package com.getjavajob.nemchinovr.dao;

import com.getjavajob.nemchinovr.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book getById(int id) {
        return getBookByField("id", id);
    }

    public List<Book> findAllBooks() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    public Book getBookByField(String field, Object o) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(builder.equal(root.get(field), o));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Book saveBook(Book book) {
        return entityManager.merge(book);
    }
}
