package view;

import models.Account;
import models.User;
import models.enums.AccountType;
import models.enums.UserType;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import service.BankService;
import service.UserService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------- Welcome ---------- ");
        System.out.println("1.sign in\n2.register\n3.exit");
        outer:
        while (true) {
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
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private static void register() {
        BankService bankService = new BankService();
        try {
            System.out.println("enter name :");
            String name=scanner.next();
            System.out.println("enter family :");
            String family=scanner.next();
            System.out.println("enter national code");
            String nationalCode=scanner.next();
            System.out.println("Account type : 1.current  2.short term  3.long term  4.good lean");
            AccountType type=AccountType.getType(scanner.nextInt());
            System.out.println("enter balance : ");
            double balance=scanner.nextDouble();
            int numberAccount=(int)createRandomInt(100000,999999);
            int cvv2=(int)createRandomInt(1000,9999);
            long numberCart=createRandomInt(100000000,999999999);
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            LocalDate localDate = LocalDate.parse(format.format(now)).plusYears(3);
            Date expDate = Date.from(localDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());


            User user=User.UserBuilder.anUser().withName(name)
                    .withFamily(family)
                    .withNationalCode(nationalCode)
                    .withUserType(UserType.NO_HISTORY)
                    .build();
            Account account=Account.AccountBuilder.anAccount().withAccountType(type)
                    .withAccountNumber(numberAccount)
                    .withCartNumber(numberCart)
                    .withCvv2(cvv2)
                    .withExpireDate(expDate)
                    .withBalance(balance)
                    .build();
            user.getAccounts().add(account);
            bankService.saveUserToDb(user);




        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void signIn() {
        BankService bankService = new BankService();
        System.out.println("enter  name :");
        String name = scanner.next();
        System.out.println("enter  family :");
        String family = scanner.next();
        String cartNum = scanner.next();
        User user = User.UserBuilder.anUser().withName(name)
                .withFamily(family)
                .build();
        int id = bankService.searchUser(user, Long.parseLong(cartNum));
        if (id != 0) {
            showMenuUser();
        } else {
            System.out.println("this user is not found !");
        }
    }

    private static void showMenuUser() {
        UserService userService = new UserService();
        System.out.println("1.with draw\n2.deposit\n3.show balance\n4.exit");
        outer:
        while (true) {
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("enter amount to with draw :");
                        userService.withDraw(scanner.nextInt());
                        break;
                    case 2:
                        System.out.println("enter amount to deposit :");
                        userService.deposit(scanner.nextInt());
                        break;
                    case 3:
                        userService.getBalance();
                    case 4:
                        break outer;
                    default:
                        throw new RuntimeException("error => enter 1 - 4");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private  static long createRandomInt(long min ,long max){
        Random randomNum = new Random();
        long n = max - min + 1;
        long i = randomNum.nextInt() % n;
        return   min + i;
    }


}
