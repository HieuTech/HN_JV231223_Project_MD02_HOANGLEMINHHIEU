package run;

import bussiness.design.IAuthentication;
import bussiness.entity.RoleName;
import bussiness.entity.User;
import bussiness.impl.AuthenticationService;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

public class LoginMenu {

    private static LoginMenu loginMenu = new LoginMenu();

    public static LoginMenu getInstance() {
        return loginMenu;
    }

    private LoginMenu() {
    }


    private static IAuthentication iAuthentication = new AuthenticationService();
    public static User user = IOFile.readPerData(IOFile.USER_LOGIN_PATH);

    public void displayLoginMenu() {

        while (true) {
            if (user == null) {
                System.out.println("++++++++++++++++++++++++MENU+++++++++++++++++++++++");
                System.out.println("1. Dang nhap");
                System.out.println("2. Dang ky");
                System.out.println("3. Thoat");
                byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.exit(1);
                    default:

                        System.out.println("Your choice out of range");
                        break;
                }
            } else {
                if (user.getRoleName().equals(RoleName.ROLE_USER)) {
                    if (user.isUserStatus()) {

                        IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                        MenuUser.getInstance().displayMenuUser();
                    } else {
                        System.out.println(ErrorAndRegex.ERROR_BLOCK_ACCOUNT);
                    }
                } else if (user.getRoleName().equals(RoleName.ROLE_ADMIN)) {

                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                    MenuAdmin.getInstance().displayMenuAdmin();
                }
                else if (user.getRoleName().equals(RoleName.ROLE_TEACHER)) {
                    if (user.isUserStatus()) {

                        IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                        MenuTeacher.getInstance().displayMenuTeacher();

                    } else {
                        System.out.println(ErrorAndRegex.ERROR_BLOCK_ACCOUNT);
                    }
                }
            }


        }


    }

    public void login() {
        System.out.println("----------Dang nhap--------------");
        System.out.println("Nhap username :");
        String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY);
        System.out.println("Nhap password :");
        String passWord = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY);
        User userLogin = iAuthentication.login(userName, passWord);
        if (userLogin != null) {
            if (userLogin.getRoleName().equals(RoleName.ROLE_USER)) {
                if (userLogin.isUserStatus()) {
                    user = userLogin;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                    MenuUser.getInstance().displayMenuUser();

                } else {
                    System.out.println(ErrorAndRegex.ERROR_BLOCK_ACCOUNT);
                }
            } else if (userLogin.getRoleName().equals(RoleName.ROLE_ADMIN)) {
                user = userLogin;
                IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                MenuAdmin.getInstance().displayMenuAdmin();
            } else if (userLogin.getRoleName().equals(RoleName.ROLE_TEACHER)) {
                if (userLogin.isUserStatus()) {
                    user = userLogin;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, user);
                    MenuTeacher.getInstance().displayMenuTeacher();

                } else {
                    System.out.println(ErrorAndRegex.ERROR_BLOCK_ACCOUNT);
                }
            } else {
                displayLoginMenu();
            }
        } else {
            System.err.println("Tai khoan hoac mat khau khong chinh xac");
            System.out.println("1. Tiep tuc dang nhap");
            System.out.println("2. Ban chua co tai khoan, Dang ky ngay");
            System.out.println("3. Thoat");
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Your choice out of range");
            }
        }

    }

    public void register() {
        System.out.println("--------------Dang ky----------------");
        User user = new User();
        iAuthentication.register(user);
        System.out.println("Dang ki thanh cong");
        login();

    }
}
