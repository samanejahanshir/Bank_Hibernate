package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDao {
    private static SessionFactory sessionFactory=null;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate_files/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
