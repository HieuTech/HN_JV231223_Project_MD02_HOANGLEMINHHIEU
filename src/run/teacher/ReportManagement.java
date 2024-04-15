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
            System.out.println("1. Numeric Result Of The Student");
            System.out.println("2. Test Exam And See Result");
            System.out.println("3. Update User Private Information");
            System.out.println("4. Display Private Information");
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
