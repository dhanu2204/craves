package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public Order save(Order order) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(order);
            tx.commit();
            return order;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error saving order: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── UPDATE ──────────────────────────────────────────────
    public Order update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Order updated = session.merge(order);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error updating order: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ID ───────────────────────────────────────────
    public Order getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Order.class, id);
        } finally {
            session.close();
        }
    }

    // ── GET ALL ─────────────────────────────────────────────
    public List<Order> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Order> query = session.createQuery("FROM Order", Order.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    // ── GET BY USER ID ──────────────────────────────────────
    public List<Order> getByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Order> query = session.createQuery(
                    "FROM Order o WHERE o.user.id = :userId ORDER BY o.orderDate DESC", Order.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    // ── GET BY RESTAURANT ID ────────────────────────────────
    public List<Order> getByRestaurantId(Long restaurantId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Order> query = session.createQuery(
                    "FROM Order o WHERE o.restaurant.id = :restaurantId ORDER BY o.orderDate DESC", Order.class);
            query.setParameter("restaurantId", restaurantId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    // ── GET BY STATUS ───────────────────────────────────────
    public List<Order> getByStatus(Order.OrderStatus status) {
        Session session = sessionFactory.openSession();
        try {
            Query<Order> query = session.createQuery(
                    "FROM Order o WHERE o.status = :status ORDER BY o.orderDate DESC", Order.class);
            query.setParameter("status", status);
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
            Order order = session.get(Order.class, id);
            if (order != null) {
                session.remove(order);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error deleting order: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
