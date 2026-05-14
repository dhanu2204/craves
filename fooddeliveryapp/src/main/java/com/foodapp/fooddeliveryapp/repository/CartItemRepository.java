package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public CartItem save(CartItem cartItem) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(cartItem);
            tx.commit();
            return cartItem;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error saving cart item: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── UPDATE ──────────────────────────────────────────────
    public CartItem update(CartItem cartItem) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CartItem updated = session.merge(cartItem);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error updating cart item: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ID ───────────────────────────────────────────
    public CartItem getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(CartItem.class, id);
        } finally {
            session.close();
        }
    }

    // ── GET BY CART ID ──────────────────────────────────────
    public List<CartItem> getByCartId(Long cartId) {
        Session session = sessionFactory.openSession();
        try {
            Query<CartItem> query = session.createQuery(
                    "FROM CartItem c WHERE c.cart.id = :cartId", CartItem.class);
            query.setParameter("cartId", cartId);
            return query.getResultList();
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
            CartItem item = session.get(CartItem.class, id);
            if (item != null) {
                session.remove(item);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error deleting cart item: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
