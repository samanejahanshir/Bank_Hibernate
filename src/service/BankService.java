package service;

import dao.AccountDao;
import dao.UserDao;
import models.Account;
import models.User;

import java.sql.SQLException;

public class BankService {
    public  int saveUserToDb(User user) throws SQLException, ClassNotFoundException {
        UserDao userDao=new UserDao();
        return userDao.save(user);
    }
    public  int saveAccountToDb(Account account){
        AccountDao accountDao=new AccountDao();
        return accountDao.save(account) ;
    }
    public User searchUser(User user,long cartNum) throws SQLException, ClassNotFoundException {
        UserDao userDao=new UserDao();
        return  userDao.getUser(user);
    }
}
