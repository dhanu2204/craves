package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public Payment save(Payment payment) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(payment);
            tx.commit();
            return payment;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error saving payment: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── UPDATE ──────────────────────────────────────────────
    public Payment update(Payment payment) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Payment updated = session.merge(payment);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error updating payment: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ID ───────────────────────────────────────────
    public Payment getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Payment.class, id);
        } finally {
            session.close();
        }
    }

    // ── GET BY ORDER ID ─────────────────────────────────────
    public Payment getByOrderId(Long orderId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Payment> query = session.createQuery(
                    "FROM Payment p WHERE p.order.id = :orderId", Payment.class);
            query.setParameter("orderId", orderId);

            List<Payment> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            }
            return results.get(0); // 1 order = 1 payment
        } finally {
            session.close();
        }
    }
}
