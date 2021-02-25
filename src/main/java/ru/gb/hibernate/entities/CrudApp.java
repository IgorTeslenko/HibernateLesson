package ru.gb.hibernate.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.hibernate.PrepareDataApp;

public class CrudApp {
    private static SessionFactory sessionFactory;

    private static void init() {
        PrepareDataApp.forcePrepareData();
        sessionFactory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private static void shutdown() {
        sessionFactory.close();
    }

    private static void createProduct(String title, float price) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity salmon = new ProductEntity(title, price);
            session.save(salmon);
            session.getTransaction().commit();
        }
    }

    

    public static void main(String[] args) {
        try {
            init();
            createProduct("Лосось", 89.95f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }

    }
}
