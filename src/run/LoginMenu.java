package run;

import bussiness.design.IAuthentication;
import bussiness.entity.User;
import bussiness.impl.AuthenticationService;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class LoginMenu {

    private static IAuthentication iAuthentication = new AuthenticationService();


    public void displayLoginMenu() {
        while (true) {
            System.out.println("++++++++++++++++++++++++MENU+++++++++++++++++++++++");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("3. Thoat");
            boolean isExit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    isExit = true;
                    break;

                default:

                    System.out.println("Your choice out of range");
                    break;
            }
            if (isExit) {
                break;
            }
        }

    }

    public void login() {
        System.out.println("----------Dang nhap--------------");
        System.out.println("Nhap username :");
        String userName  = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY);
        System.out.println("Nhap password :");
        String passWord  = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY);

        User userLogin = iAuthentication.login(userName, passWord);
        if(userLogin != null){
            if(userLogin.getRoleName().)
        }

    }

    public void register(){

    }
}
