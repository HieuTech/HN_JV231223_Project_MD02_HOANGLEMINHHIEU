package run.admin;

import bussiness.design.IAdmin;
import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.impl.ExamService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class AdminManagement {

    private static AdminManagement adminManagement = new AdminManagement();

    public static AdminManagement getInstance(){
        return adminManagement;
    }
    private AdminManagement(){};

    public void displayAdminManagement(IAdmin iAdmin) {
        while (true) {
            System.out.printf("Welcome To %-5s Admin Management Page \n", LoginMenu.user.getUserName());
            System.out.println("1. Display All User ");
            System.out.println("2. Block/ UnBlock User  ");
            System.out.println("3. Find User By Name  ");
            System.out.println("4. Quit");
            boolean isExit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    iAdmin.displayAllUser();
                    break;
                case 2:
                    iAdmin.blockAndUnblockUser();
                    break;
                case 3:
                    iAdmin.findUserByName();
                    break;
                case 4:
                    isExit = true;
                    break;

                default:
                    System.out.println("Your choice out of range");
                    break;

            }
            if (isExit) {
                break;
            }
        }

    }

}
