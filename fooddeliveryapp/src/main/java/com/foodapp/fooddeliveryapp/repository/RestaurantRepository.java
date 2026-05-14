package com.foodapp.fooddeliveryapp.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
import com.foodapp.fooddeliveryapp.model.Restaurant;
import org.hibernate.query.Query;

@Repository
public class RestaurantRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Restaurant save(Restaurant restaurant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(restaurant);
            transaction.commit();
            return restaurant;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in saving restaurant " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Restaurant update(Restaurant restaurant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Restaurant updated = session.merge(restaurant);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in updating restaurant " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Restaurant getRestaurantById(long id) {
        Session session = sessionFactory.openSession();
        try {
            Restaurant restaurant = session.get(Restaurant.class, id);
            return restaurant;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting restaurant by id " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<Restaurant> getAllRestaurants() {
        Session session = sessionFactory.openSession();
        try {
            Query<Restaurant> query = session.createQuery("From Restaurant", Restaurant.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<Restaurant> getByCity(String city) {
        Session session = sessionFactory.openSession();
        try {
            Query<Restaurant> query = session.createQuery("From Restaurant where city = :city", Restaurant.class);
            query.setParameter("city", city);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<Restaurant> getByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            Query<Restaurant> query = session.createQuery("From Restaurant r where LOWER(r.name) like LOWER(:name)",
                    Restaurant.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Restaurant restaurant = session.get(Restaurant.class, id);
            if (restaurant != null)
                session.remove(restaurant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in deleting restaurant " + e.getMessage());
        } finally {
            session.close();
        }
    }

}
