package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public Category save(Category category) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(category);
            tx.commit();
            return category;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error saving category: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── UPDATE ──────────────────────────────────────────────
    public Category update(Category category) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Category updated = session.merge(category);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error updating category: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ID ───────────────────────────────────────────
    public Category getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Category.class, id);
        } finally {
            session.close();
        }
    }

    // ── GET ALL ─────────────────────────────────────────────
    public List<Category> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Category> query = session.createQuery("FROM Category", Category.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    // ── GET BY NAME ─────────────────────────────────────────
    public Category getByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            Query<Category> query = session.createQuery(
                "FROM Category c WHERE c.name = :name", Category.class
            );
            query.setParameter("name", name);
            
            List<Category> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            }
            return results.get(0);
        } finally {
            session.close();
        }
    }

    // ── DELETE BY ID ────────────────────────────────────────
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.remove(category);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error deleting category: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
