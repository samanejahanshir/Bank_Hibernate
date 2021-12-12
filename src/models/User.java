package models;

import models.enums.UserType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String name;
    private  String family;
    private  String nationalCode;
    private UserType userType;
    private Date createUserDate;
    private  Date infoUpdateLastDate;
    private Map<String,Date> listOfUpdates;
    @ManyToOne(cascade = CascadeType.ALL)
    private List<Account> accounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Date getCreateUserDate() {
        return createUserDate;
    }

    public void setCreateUserDate(Date createUserDate) {
        this.createUserDate = createUserDate;
    }

    public Date getInfoUpdateLastDate() {
        return infoUpdateLastDate;
    }

    public void setInfoUpdateLastDate(Date infoUpdateLastDate) {
        this.infoUpdateLastDate = infoUpdateLastDate;
    }

    public Map<String, Date> getListOfUpdates() {
        return listOfUpdates;
    }

    public void setListOfUpdates(Map<String, Date> listOfUpdates) {
        this.listOfUpdates = listOfUpdates;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
