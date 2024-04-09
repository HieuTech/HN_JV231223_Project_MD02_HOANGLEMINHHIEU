package run;

import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.entity.RoleName;
import bussiness.impl.ExamService;
import bussiness.impl.TeacherService;
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

    private static ITeacher iTeacher = new TeacherService();

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
                    ExamService.examList.forEach(Exam::displayData);
                    System.out.println("Do you want to see detail? | 1. YES  | 2. NO");
                    byte choice1 = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                    if (choice1 == 1) {
                        iTeacher.seeDetailExam();
                    }
                    break;

                case 2:
                    iTeacher.addNewELement();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    LoginMenu.user = null;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, LoginMenu.user);
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

