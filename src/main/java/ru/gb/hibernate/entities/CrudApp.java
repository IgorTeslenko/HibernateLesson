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
            ProductEntity product = new ProductEntity(title, price);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    private static void getProduct(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    private static void updateProduct(long id, String title, float price) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            product.setPrice(price);
            product.setTitle(title);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    private static void deleteProduct(long id) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            createProduct("Лосось", 89.95f);
            getProduct(13L);
            updateProduct(15L, "Карась", 67.85f);
            deleteProduct(12);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }

    }
}
