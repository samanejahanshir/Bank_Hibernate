package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AccessDao {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null){
            sessionFactory=new Configuration().configure("hibernate_files/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
