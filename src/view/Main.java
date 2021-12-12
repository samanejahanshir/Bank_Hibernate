package view;

import dao.UserDao;
import models.Account;
import models.User;
import models.enums.AccountType;
import models.enums.UserType;
import service.BankService;
import service.UserService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------- Welcome ---------- ");
        outer:
        while (true) {
            System.out.println("1.sign in\n2.register\n3.exit");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        signIn();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        break outer;
                    default:
                        throw new RuntimeException("error => enter 1 - 3");
                }
            } catch (RuntimeException | SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void register() {
        BankService bankService = new BankService();
        try {
            System.out.println("enter name :");
            String name = scanner.next();
            System.out.println("enter family :");
            String family = scanner.next();
            System.out.println("enter national code");
            String nationalCode = scanner.next();
            User user = User.UserBuilder.anUser().withName(name)
                    .withFamily(family)
                    .withNationalCode(nationalCode)
                    .withUserType(UserType.NO_HISTORY)
                    .build();
            Account account = createAccount(user);
            user.getAccounts().add(account);
            bankService.saveUserToDb(user);
            // bankService.saveAccountToDb(account);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void signIn() throws SQLException, ClassNotFoundException {
        BankService bankService = new BankService();
        System.out.println("enter  name :");
        String name = scanner.next();
        System.out.println("enter  family :");
        String family = scanner.next();
       /* System.out.println("enter  cart number :");
        String cartNum = scanner.next();*/
        User user = User.UserBuilder.anUser().withName(name)
                .withFamily(family)
                .build();
        User user1 = bankService.searchUser(user, 3456);
        if (user1 != null) {
            showMenuUser(user1);
        } else {
            System.out.println("this user is not found !");
        }
    }

    private static void showMenuUser(User user) {
        UserService userService = new UserService();
        outer:
        while (true) {
            try {
                System.out.println("1.with draw\n2.deposit\n3.show balance\n4.create new account\n5.edit information\n6.exit");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("enter amount to with draw :");
                        int amountWithdraw = scanner.nextInt();
                        System.out.println("enter account number : ");
                        int accountNumberWithDraw = scanner.nextInt();
                        if (userService.withDraw(user, amountWithdraw, accountNumberWithDraw)) {
                            System.out.println("with draw was successfully");
                        }
                        break;
                    case 2:
                        System.out.println("enter amount to deposit :");
                        int amountDeposit = scanner.nextInt();
                        System.out.println("enter account number : ");
                        int accountNumberDeposit = scanner.nextInt();
                        if (userService.deposit(user, amountDeposit, accountNumberDeposit)) {
                            System.out.println("deposit is successfully");
                        }
                        break;
                    case 3:
                        System.out.println("enter account number : ");
                        int accountNumber = scanner.nextInt();
                        System.out.println("balance : " + userService.getBalance(user, accountNumber));
                        break;
                    case 4:
                        user.getAccounts().add(createAccount(user));
                        if (userService.createAccount(user) == 1) {
                            System.out.println("create account is successfully");
                        }
                        break;
                    case 5:
                        editInformation(user);
                    case 6:
                        break outer;
                    default:
                        throw new RuntimeException("error => enter 1 - 4");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();

            }
        }
    }

    private static long createRandomInt(long min, long max) {
        Random randomNum = new Random();
        long n = max - min + 1;
        long i = randomNum.nextInt() % n;
        return min + i;
    }

    private static Account createAccount(User user) {
        System.out.println("Account type : 1.current  2.short term  3.long term  4.good lean");
        AccountType type = AccountType.getType(scanner.nextInt());
        System.out.println("enter balance : ");
        double balance = scanner.nextDouble();
        int numberAccount = (int) createRandomInt(100000, 999999);
        if (numberAccount < 0) {
            numberAccount *= -1;
        }
        int cvv2 = (int) createRandomInt(1000, 9999);
        if (cvv2 < 0) {
            cvv2 *= -1;
        }
        long numberCart = createRandomInt(100000000, 999999999);
        if (numberCart < 0) {
            numberCart *= -1;
        }
        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(format.format(now)).plusYears(3);
        Date expDate = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        Account account = Account.AccountBuilder.anAccount().withAccountType(type)
                .withAccountNumber(numberAccount)
                .withCartNumber(numberCart)
                .withCvv2(cvv2)
                .withExpireDate(expDate)
                .withUser(user)
                .withBalance(balance)
                .build();
        return account;
    }

    private static void editInformation(User user) {
        String filedUpdate;
        outer:
        while (true) {
            System.out.println("enter number :\n1.edit name\n2.edit family\n3.edit national code\n4.exit");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("enter name :");
                        String name = scanner.next();
                        user.setName(name);
                        filedUpdate = "name";
                        break;
                    case 2:
                        System.out.println("enter family :");
                        String family = scanner.next();
                        user.setFamily(family);
                        filedUpdate = "family";
                        break;
                    case 3:
                        System.out.println("enter national code :");
                        String nationalCode = scanner.next();
                        user.setNationalCode(nationalCode);
                        filedUpdate = "national code";
                        break;
                    case 4:
                        break outer;
                    default:
                        throw new RuntimeException("enter 1 - 4 please !");
                }
                UserService userService = new UserService();
                userService.updateInformation(user, filedUpdate);
                System.out.println(filedUpdate + " was update");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
