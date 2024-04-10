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
            System.out.println("6. Bắt đầu làm bài thi");
            System.out.println("7. Xem kết quả làm bài thi");
            System.out.println("8. Đăng xuất");


            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    if (ExamService.examList.isEmpty()) {
                        System.out.println("Exam List Is Empty");
                    } else {
                        ExamService.examList.forEach(Exam::displayData);
                        System.out.println("Do you want to see detail? | 1. YES  | 2. NO");
                        byte choice1 = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                        if (choice1 == 1) {
                            iTeacher.seeDetailExam();
                        }
                    }
                    break;

                case 2:
                    iTeacher.addNewELement();
                    break;
                case 3:
                    iTeacher.editElement();
                    break;
                case 4:
                    iTeacher.deleteElement();
                    break;
                case 5:
                    iTeacher.numericUserTakeExam();
                    break;
                case 6:
                    iTeacher.startExam();
                    break;
                case 7:
                    iTeacher.seeResultExam();
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

