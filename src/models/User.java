package models;

import models.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private String nationalCode;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @CreationTimestamp
    private Date createUserDate;
    @UpdateTimestamp
    private Date infoUpdateLastDate;
    private String listOfUpdates="";
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts=new ArrayList<>();

    public static final class UserBuilder {
        private int id;
        private String name;
        private String family;
        private String nationalCode;
        private UserType userType;
        private Date createUserDate;
        private Date infoUpdateLastDate;
        private List<Account> accounts=new ArrayList<>();

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withFamily(String family) {
            this.family = family;
            return this;
        }

        public UserBuilder withNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public UserBuilder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserBuilder withCreateUserDate(Date createUserDate) {
            this.createUserDate = createUserDate;
            return this;
        }

        public UserBuilder withInfoUpdateLastDate(Date infoUpdateLastDate) {
            this.infoUpdateLastDate = infoUpdateLastDate;
            return this;
        }

        public UserBuilder withAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.nationalCode = this.nationalCode;
            user.id = this.id;
            user.createUserDate = this.createUserDate;
            user.family = this.family;
            user.userType = this.userType;
            user.infoUpdateLastDate = this.infoUpdateLastDate;
            user.accounts = this.accounts;
            return user;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public UserType getUserType() {
        return userType;
    }

    public Date getCreateUserDate() {
        return createUserDate;
    }

    public Date getInfoUpdateLastDate() {
        return infoUpdateLastDate;
    }

   /* public Map<Date, String> getListOfUpdates() {
        return listOfUpdates;
    }*/

    public String getListOfUpdates() {
        return listOfUpdates;
    }

    public void setListOfUpdates(String listOfUpdates) {
        this.listOfUpdates += listOfUpdates+",";
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", userType=" + userType +
                ", createUserDate=" + createUserDate +
                ", infoUpdateLastDate=" + infoUpdateLastDate +
                ", listOfUpdate=" + listOfUpdates +
                ", accounts=" + accounts +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
