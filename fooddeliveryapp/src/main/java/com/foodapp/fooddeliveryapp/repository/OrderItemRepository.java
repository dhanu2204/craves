package com.foodapp.fooddeliveryapp.repository;

import com.foodapp.fooddeliveryapp.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // ── SAVE ────────────────────────────────────────────────
    public OrderItem save(OrderItem orderItem) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(orderItem);
            tx.commit();
            return orderItem;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error saving order item: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // ── GET BY ORDER ID ─────────────────────────────────────
    public List<OrderItem> getByOrderId(Long orderId) {
        Session session = sessionFactory.openSession();
        try {
            Query<OrderItem> query = session.createQuery(
                    "FROM OrderItem o WHERE o.order.id = :orderId", OrderItem.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }
}
