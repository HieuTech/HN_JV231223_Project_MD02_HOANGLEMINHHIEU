package run.admin;

import bussiness.design.IAdmin;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.QuizConFig;

public class NumericReport {

    private static NumericReport numericReport = new NumericReport();

    public static NumericReport getInstance() {
        return numericReport;
    }

    private NumericReport() {
    }

    ;


    public void displayNumericReport(IAdmin iAdmin) {
        while (true) {
            System.out.printf("Welcome To %-5s Numeric Report Page \n", LoginMenu.user.getUserName());
            System.out.println("1. thống kê danh sách người đã tham gia thi trong tháng ");
            System.out.println("2. thống kê danh sách bài thi được tạo ra trong tháng ");
            System.out.println("3. thống kê 2 bài thi được nhiều người thi nhất trong tháng ");
            System.out.println("4. thống kê 2 bài thi được tìm kiếm nhiều nhất trong tháng  ");
            System.out.println("5. hiển thị 2 student có điểm thi cao nhất trong tháng");
            System.out.println("6. thống kê 2 bài thi được tìm kiếm ít nhất trong tháng ");
            System.out.println("7. Câu hỏi trả lời sai nhiều nhất trong 1 bài thi");
            System.out.println("8. Câu hỏi trả lời đúng nhiều nhất trong 1 bài thi");
            System.out.println("9. Số tài khoản User được tạo ra trong tháng");
            System.out.println("10. Số tài khoản Teacher được tạo ra trong tháng");
            System.out.println("11. hiển thị 2 student có điểm thi thấp trong tháng");
            System.out.println("12. Quit");
            boolean isExit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    iAdmin.staticalStudentTookExamInMonth();
                    break;
                case 2:
                    iAdmin.staticalExamCreatedInMonth();
                    break;
                case 3:
                    iAdmin.findExamMostStudentTookInMonth();
                    break;
                case 4:
                    iAdmin.findExamWithTheLeastNumberPeopleLookFor();
                    break;
                case 5:
                    iAdmin.findStudentWhoGetHighestScore();
                    break;
                case 6:
                    iAdmin.findStudentWhoGetHighestScore();
                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;

                case 11:
                    iAdmin.findStudentWhoGetLowestScore();
                    break;
                case 12:
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
