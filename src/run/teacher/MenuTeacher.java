package run.teacher;

import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.impl.ExamService;
import bussiness.impl.TeacherService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

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
            System.out.printf("Welcome %-5s \n", LoginMenu.user.getUserName());

            System.out.println("1. Exam Management");
            System.out.println("2. Report Management");
            System.out.println("3. Sign Out");
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    ExamManagement.getInstance().displayExamManagement(iTeacher);
                    break;
                case 2:
                    ReportManagement.getInstance().displayReportManagement(iTeacher);
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

