package models;

import models.enums.AccountType;
import models.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accountNumber;
    private long cartNumber;
    @CreationTimestamp
    private Date createAccount;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private double balance;
    private int cvv2;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_userId")
    private User user;
    @Transient
    private final int countTransaction = 3;
    //  private Map<Date,TransactionType> transactions;



    public static final class AccountBuilder {
        private int id;
        private int accountNumber;
        private long cartNumber;
        private Date createAccount;
        private AccountType accountType;
        private double balance;
        private int cvv2;
        private Date expireDate;
        @ManyToOne(cascade = CascadeType.ALL)
        private User user;
     //   private Map<Date, TransactionType> transactions;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder withCartNumber(long cartNumber) {
            this.cartNumber = cartNumber;
            return this;
        }

        public AccountBuilder withCreateAccount(Date createAccount) {
            this.createAccount = createAccount;
            return this;
        }

        public AccountBuilder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public AccountBuilder withBalance(double balance) {
            this.balance = balance;
            return this;
        }
        public AccountBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AccountBuilder withCvv2(int cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public AccountBuilder withExpireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }

      /*  public AccountBuilder withCountTransaction(int countTransaction) {
            this.countTransaction = countTransaction;
            return this;
        }*/

       /* public AccountBuilder withTransactions(Map<Date, TransactionType> transactions) {
            this.transactions = transactions;
            return this;
        }*/

        public Account build() {
            Account account = new Account();
            account.cartNumber = this.cartNumber;
            account.accountNumber = this.accountNumber;
            //  account.transactions = this.transactions;
            account.cvv2 = this.cvv2;
            account.expireDate = this.expireDate;
            account.accountType = this.accountType;
            account.balance = this.balance;
            account.createAccount = this.createAccount;
            account.id = this.id;
            return account;
        }
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public long getCartNumber() {
        return cartNumber;
    }

    public Date getCreateAccount() {
        return createAccount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public int getCvv2() {
        return cvv2;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public int getCountTransaction() {
        return countTransaction;
    }

   /* public Map<Date, TransactionType> getTransactions() {
        return transactions;
    }*/

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", cartNumber=" + cartNumber +
                ", createAccount=" + createAccount +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", cvv2=" + cvv2 +
                ", expireDate=" + expireDate +
                ", countTransaction=" + countTransaction +
                ", user=" + user +
                '}';
    }
}
