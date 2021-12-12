package service;

import dao.UserDao;
import models.User;

public class BankService {
    public  int saveUserToDb(User user){
        UserDao userDao=new UserDao();
        return userDao.save(user);
    }
    public int searchUser(User user,long cartNum){

return  0;
    }
}
