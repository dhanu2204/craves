package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public Cart save(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(cart);
            tx.commit();
            return cart;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error saving cart: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── UPDATE ──────────────────────────────────────────────
    public Cart update(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Cart updated = session.merge(cart);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error updating cart: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ID ───────────────────────────────────────────
    public Cart getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Cart.class, id);
        } finally {
            session.close();
        }
    }

    // ── GET BY USER ID ──────────────────────────────────────
    public Cart getByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        try {
            // Note: Cart has a 'user' object, so we query 'c.user.id'
            Query<Cart> query = session.createQuery(
                "FROM Cart c WHERE c.user.id = :userId", Cart.class
            );
            query.setParameter("userId", userId);
            
            List<Cart> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            }
            return results.get(0); // A user only has 1 cart
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
            Cart cart = session.get(Cart.class, id);
            if (cart != null) {
                session.remove(cart);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error deleting cart: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
