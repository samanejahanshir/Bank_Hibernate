package dao;

import models.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDao {
    public int save(Account account) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(account);
        transaction.commit();
        session.close();
        return id;
    }
}
