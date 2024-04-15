package run.user;

import bussiness.design.IUser;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class AccountManagement {
    private static AccountManagement accountManagement = new AccountManagement();

    public static AccountManagement getInstance(){
        return accountManagement;
    };

    private AccountManagement(){}

    public void displayAccountManagement(IUser iUser){
        while (true) {
            System.out.printf("Welcome To %-5s Account Management Page \n", LoginMenu.user.getUserName());
            System.out.println("1. Display Private Information");
            System.out.println("2. Update Private Information");
            System.out.println("3. Display History Of Exam And Review Exam");
            System.out.println("4. Change Password");
            System.out.println("5. Quit");
            boolean isExit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    iUser.displayInfo();
                    break;
                case 2:
                    iUser.updateInfo();
                    break;
                case 3:
                    iUser.seeResultExam();
                    break;
                case 4:
                    iUser.changePassword();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("Your choice out of range");
                    break;
            }
            if (isExit){
                break;
            }
        }

    }

}
