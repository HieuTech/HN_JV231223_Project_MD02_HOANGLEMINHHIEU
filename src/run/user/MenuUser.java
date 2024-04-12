package run.user;

import bussiness.design.IUser;
import bussiness.impl.UserService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

public class MenuUser {

    private static MenuUser menuUser = new MenuUser();

    public static MenuUser getInstance() {
        return menuUser;
    }

    private MenuUser() {
    }

    private static IUser iUser = new UserService();


    public static void setiUser(IUser iUser) {
        MenuUser.iUser = iUser;
    }

    public void displayMenuUser() {
        while (true) {
            System.out.printf("Welcome %-5s \n", LoginMenu.user.getUserName());
            System.out.println("1. Go To Home Page");
            System.out.println("2. Account Management");
            System.out.println("3. Sign Out");
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    HomePage.getInstance().displayUserHomePage(iUser);
                    break;
                case 2:
                    AccountManagement.getInstance().displayAccountManagement(iUser);
                    break;
                case 3:
                    LoginMenu.user = null;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, LoginMenu.user);
                    return;

                default:
                    System.out.println("Your choice out of range");
                    break;

            }
        }
    }
}
