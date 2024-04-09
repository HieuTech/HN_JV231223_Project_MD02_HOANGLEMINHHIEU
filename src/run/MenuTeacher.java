package run;

import bussiness.entity.RoleName;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.awt.*;

public class MenuTeacher {

    private static MenuTeacher menuTeacher = new MenuTeacher();

    public static MenuTeacher getInstance() {
        return menuTeacher;
    }

    private MenuTeacher() {
    }

    public void displayMenuTeacher() {
        while (true) {
            System.out.println("Welcome to teacher page");
            System.out.println("1. Hiển thị danh sách đề thi");
            System.out.println("2. Thêm mới đề thi");
            System.out.println("3. Chỉnh sửa đề thi");
            System.out.println("4. Xóa đề thi");
            System.out.println("5. Thống kê kết quả thi của người dự thi");
            System.out.println("6. Đăng xuất");


            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    LoginMenu.user = null;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH,LoginMenu.user);
                    return;
                case 7:

                    break;
                case 8:

                    break;
                default:
                    System.out.println("Your choice out of range");
                    break;

            }

        }

    }


}

