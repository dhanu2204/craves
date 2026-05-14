package com.foodapp.fooddeliveryapp.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapp.fooddeliveryapp.model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in saving user " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public User update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User updated = session.merge(user);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in updating user " + e.getMessage());
        }
        finally {
            session.close();
        }
    }

    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in getting user by id " + e.getMessage());
        }
        finally {
            session.close();
        }
    }

    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting user by email " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery("From User", User.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            User user = session.get(User.class, id);
            if (user != null) {

                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in deleting user " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
