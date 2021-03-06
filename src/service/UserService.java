package service;

import dao.UserDao;
import models.Account;
import models.User;

import java.sql.SQLException;
import java.util.Date;

public class UserService {
    public boolean withDraw(User user, int amount, int accountNumber) throws SQLException, ClassNotFoundException {
        Account account = user.getAccounts().stream().filter(account1 -> account1.getAccountNumber() == accountNumber).findAny().get();
        if (account.getBalance() > amount) {
            user.getAccounts().remove(account);
            account.setBalance(account.getBalance() - amount);
            account.setTransactions("withDraw "+amount+" :  "+new Date());
            user.getAccounts().add(account);
            UserDao userDao = new UserDao();
            userDao.update(user);
            return true;
        } else {
            System.out.println("your balance is not enough");
            return false;
        }
    }

    public boolean deposit(User user, int amount, int accountNumber) throws SQLException, ClassNotFoundException {
        Account account = user.getAccounts().stream().filter(account1 -> account1.getAccountNumber() == accountNumber).findAny().get();
        user.getAccounts().remove(account);
        account.setBalance(account.getBalance() + amount);
        account.setTransactions("deposit "+amount+" :  "+new Date());
        user.getAccounts().add(account);
        UserDao userDao = new UserDao();
        userDao.update(user);
        return true;
    }

    public double getBalance(User user, int accountNumber) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        user = userDao.getUser(user);
        return user.getAccounts().stream().filter(account -> account.getAccountNumber()==accountNumber).findAny().get().getBalance();
    }

    public int createAccount(User user) {
        UserDao userDao = new UserDao();
        return userDao.update(user);
    }
    public void updateInformation(User user,String filedUpdate){
        UserDao userDao=new UserDao();
        user.setListOfUpdates(filedUpdate+" was edited :  "+new Date());
        userDao.update(user);


    }
}
