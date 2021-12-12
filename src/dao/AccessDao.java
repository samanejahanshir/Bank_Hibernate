package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDao {
    private static SessionFactory sessionFactory;
    private Connection connection;

    public AccessDao() throws SQLException, ClassNotFoundException {
        connection = getConnection();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_hibernate", "root", "1234567890");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate_files/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
