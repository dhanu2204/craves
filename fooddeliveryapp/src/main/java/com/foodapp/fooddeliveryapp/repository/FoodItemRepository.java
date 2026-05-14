package com.foodapp.fooddeliveryapp.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapp.fooddeliveryapp.model.FoodItem;

@Repository
public class FoodItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public FoodItem save(FoodItem foodItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(foodItem);
            transaction.commit();
            return foodItem;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in saving food item " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public FoodItem update(FoodItem foodItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(foodItem);
            transaction.commit();
            return foodItem;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in updating food item " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public FoodItem getById(long id) {
        Session session = sessionFactory.openSession();
        try {
            FoodItem foodItem = session.get(FoodItem.class, id);
            return foodItem;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting food item " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<FoodItem> getAllFoodItems() {
        Session session = sessionFactory.openSession();
        try {
            Query<FoodItem> query = session.createQuery("From FoodItem", FoodItem.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<FoodItem> getByRestaurantId(long restaurantId) {
        Session session = sessionFactory.openSession();
        try {
            Query<FoodItem> query = session.createQuery("From FoodItem f where f.restaurant.id = :restaurantId",
                    FoodItem.class);
            query.setParameter("restaurantId", restaurantId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            FoodItem foodItem = session.get(FoodItem.class, id);
            if (foodItem != null)
                session.remove(foodItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException("Error in deleting food item " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
