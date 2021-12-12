package models;

import models.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int accountNumber;
    private long cartNumber;
    private Date createAccount;
    private AccountType accountType;
    private  double balance;
    private  int cvv2;
    private  Date expireDate;

}
