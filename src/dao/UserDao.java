package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;


public class UserDao extends AccessDao {
    public UserDao()  {
    }

    public int save(User user) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    public int update(User user) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return 1;
    }

    public User getUser(User user){
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> userList = session.createQuery(criteria).getResultList();
        User user1 = userList.stream().filter(user2 -> user2.getName().equalsIgnoreCase(user.getName())
                       && user2.getFamily().equalsIgnoreCase(user.getFamily())).findAny().get();
        System.out.println(user1);
        transaction.commit();
        session.close();
        return user1;


    }
}
