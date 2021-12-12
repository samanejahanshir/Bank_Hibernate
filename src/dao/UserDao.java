package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {
    public int save(User user) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }
}
