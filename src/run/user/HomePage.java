package run.user;

import bussiness.design.IUser;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class HomePage {

    private static HomePage homePage = new HomePage();

    public static HomePage getInstance(){
        return homePage;
    }

    private HomePage(){};

    public void displayUserHomePage(IUser iUser){
        while (true) {
            System.out.printf("Welcome To %-5s Home Page \n", LoginMenu.user.getUserName());
            System.out.println("1. Display Public Exam");
            System.out.println("2. Find Exam By Catalog Name Or Exam Name");
            System.out.println("3. Start Exam");
            System.out.println("4. Quit");
            boolean isExit = false;
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
