package run;

import bussiness.design.IUser;
import bussiness.impl.UserService;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.sql.SQLOutput;

public class MenuUser {

    private static MenuUser menuUser = new MenuUser();

    public static MenuUser getInstance() {
        return menuUser;
    }

    private MenuUser() {
    }

    private static IUser iUser = new UserService();


    public void displayMenuUser() {
        while (true) {
            System.out.println("Welcome to user page");
            System.out.println("1. Hiển thị danh sách đề thi");
            System.out.println("2. Tìm kiếm đề thi theo danh mục");
            System.out.println("3. Bắt đầu thi");
            System.out.println("4. Hiển thị thông tin cá nhân");
            System.out.println("5. Chỉnh sửa thông tin cá nhân");
            System.out.println("6. Hiển thị lịch sử bài thi và review bài thi");
            System.out.println("7. Đồi mật khẩu");
            System.out.println("8. Đăng xuất");

            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    iUser.displayPublicExam();
                    break;
                case 2:
                    iUser.findExamByName();
                    break;
                case 3:
                    iUser.startExam();
                    break;
                case 4:
                    iUser.displayInfo();
                    break;
                case 5:
                    iUser.updateInfo();
                    break;
                case 6:
                    iUser.seeResultExam();
                    break;
                case 7:
                    iUser.changePassword();
                    break;
                case 8:
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
