package run.teacher;

import bussiness.design.ITeacher;
import bussiness.design.IUser;
import bussiness.entity.Exam;
import bussiness.impl.ExamService;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class ExamManagement {
    private static ExamManagement examManagement = new ExamManagement();

    public static ExamManagement getInstance() {
        return examManagement;
    }

    private ExamManagement() {
    }
    public void displayExamManagement(ITeacher iTeacher) {
        while (true) {
            System.out.printf("Welcome To %-5s Exam Management Page \n", LoginMenu.user.getUserName());
            System.out.println("1. Display All Exam");
            System.out.println("2. Add New Exam");
            System.out.println("3. Update Exam");
            System.out.println("4. Delete Exam");
            System.out.println("5. Quit");
            boolean isExit = false;
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
