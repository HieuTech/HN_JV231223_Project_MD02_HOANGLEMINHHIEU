package run.admin;

import bussiness.design.IAdmin;
import bussiness.impl.AdminService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

public class MenuAdmin {

    private static MenuAdmin menuAdmin = new MenuAdmin();

    public static MenuAdmin getInstance() {
        return menuAdmin;
    }

    //mục đích Signleton là ko cho phép khởi tạo đối tượng, mà chỉ truy cập đối tượng qua getInstance,
    //vì vậy cần khởi tạo lại constructor với private. nếu ko
    private MenuAdmin() {

    }

    private static IAdmin iAdmin = new AdminService();

    public void displayMenuAdmin() {
        while (true) {
            System.out.printf("-----Welcome %-5s---- \n", LoginMenu.user.getUserName());

            System.out.println("1. Admin Management ");
            System.out.println("2. Numeric Report ");
            System.out.println("3. Sign Out ");
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    AdminManagement.getInstance().displayAdminManagement(iAdmin);
                    break;
                case 2:
                    NumericReport.getInstance().displayNumericReport(iAdmin);
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
