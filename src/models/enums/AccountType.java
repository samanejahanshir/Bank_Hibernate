package models.enums;

public enum AccountType {
    CURRENT_ACCOUNT(1),SHORT_TERM_ACCOUNT(2),LONG_TERM_ACCOUNT(3),GOOD_LOAN(4);
    private  int index;

    AccountType(int index) {
        this.index = index;
    }

    public static AccountType getType(int index){
        switch (index){
            case 1:
                return CURRENT_ACCOUNT;
            case 2:
                return SHORT_TERM_ACCOUNT;
            case 3 :
                return  LONG_TERM_ACCOUNT;
            case 4:
                return GOOD_LOAN;
            default:
                throw new RuntimeException("this index not exist in type accounts");
        }

    }
}
