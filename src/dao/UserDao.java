package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class UserDao {
    public int save(User user) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }
   /* public  int getUser(User user,long cartNum){
        Session session = AccessDao.getSessionFactory().openSession();
        //Query query = session.createQuery("from User join  Account where User );

    }*/
}
