package run.teacher;

import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.impl.ExamService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class ReportManagement {

    private static ReportManagement reportManagement = new ReportManagement();
    public  static  ReportManagement getInstance(){
        return  reportManagement;
    }
private ReportManagement(){};

    public void displayReportManagement(ITeacher iTeacher){
        while (true) {
            System.out.printf("Welcome To %-5s Report Management Page \n", LoginMenu.user.getUserName());
            System.out.println("1. Thống kê kết quả thi của người dự thi");
            System.out.println("2. Kiểm tra thi thử và xem kết quả");
            System.out.println("3. Chỉnh sửa thông tin cá nhân");
            System.out.println("4. Xem thông tin cá nhân");
            System.out.println("5. Quit");
            boolean isExit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    iTeacher.numericUserTakeExam();
                    break;
                case 2:
                    iTeacher.startExam();
                    break;
                case 3:
                    iTeacher.updateInfo();
                    break;
                case 4:
                    iTeacher.displayInfo();
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
