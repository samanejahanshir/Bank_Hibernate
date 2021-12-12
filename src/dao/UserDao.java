package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;


public class UserDao extends AccessDao {
    public UserDao() throws SQLException, ClassNotFoundException {
    }

    public int save(User user) {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    public User getUser(User user, long cartNum) throws SQLException, ClassNotFoundException {
        Session session = AccessDao.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class, 1);
        System.out.println(user1);
        transaction.commit();
        session.close();
       /* User user1=null;
        if(getConnection()!=null){
            PreparedStatement statement = getConnection().prepareStatement("select * from user inner join user_account inner  join account where name =? and family=?");
            statement.setString(1,user.getName());
            statement.setString(2,user.getFamily());
            ResultSet resultSet = statement.executeQuery();
            Account account=Account.AccountBuilder.anAccount().withAccountType(AccountType.valueOf(resultSet.getString("accountType")))
                    .withBalance(resultSet.getDouble("balance"))
                    .withAccountNumber(resultSet.getInt("accountNumber"))
                    .withCreateAccount(resultSet.getDate("createAccount"))
                    .withCartNumber(resultSet.getInt("cartNumber"))
                    .withCvv2(resultSet.getInt("cvv2"))
                    .build();
            user1=User.UserBuilder.anUser().withName(resultSet.getString("name"))
                    .withFamily(resultSet.getString("family"))
                    .withNationalCode(resultSet.getString("nationalCode"))
                    .withCreateUserDate(resultSet.getDate("createUserDate"))
                    .withId(resultSet.getInt("id")).build();
            user1.getAccounts().add(account);

        }*/
        return user1;


    }
}
