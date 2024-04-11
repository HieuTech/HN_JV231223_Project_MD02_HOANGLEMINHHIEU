package bussiness.impl;

import bussiness.design.IUser;
import bussiness.entity.*;
import org.mindrot.jbcrypt.BCrypt;
import run.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.util.List;

public class UserService implements IUser {
    public static List<User> userList;

    static {
        userList = IOFile.readData(IOFile.USER_PATH);
    }


    @Override
    public Exam findExamById(Integer id) {
        return ExamService.examList.stream().filter(exam -> exam.getExamId() == id).findFirst().orElse(null);

    }

    @Override
    public void changePassword() {
        System.out.println("Input email");
        String email = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_EMAIL, ErrorAndRegex.ERROR_VALUE);
        User userChangePassword = UserService.userList.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
        if (userChangePassword != null) {
            while (true) {
                System.out.println("Input New Password");

                String newPassword = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_PASSWORD, ErrorAndRegex.ERROR_VALUE);
                System.out.println("Retype New Password");
                String reTypeNewPassword = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_PASSWORD, ErrorAndRegex.ERROR_VALUE);
                if (newPassword.equals(reTypeNewPassword)) {

                    userChangePassword.setPassword(BCrypt.hashpw(reTypeNewPassword, BCrypt.gensalt(5)));
                    UserService.userList.set(UserService.userList.indexOf(userChangePassword), userChangePassword);
                    IOFile.writeData(IOFile.USER_PATH, UserService.userList);
                    System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
                    break;
                } else {
                    System.out.println("Password not match");
                }
            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findExamByName() {
        System.out.println("Input Catalog Name Or Exam Title To Look For");
        String inputSearch = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        if (ExamService.examList.stream().anyMatch(exam -> exam.getDescription().contains(inputSearch))) {
//            ExamService.examList.stream().filter(exam -> exam.getDescription().contains(inputSearch)).forEach(Exam::displayData);
            List<Exam> exams = ExamService.examList.stream().filter(exam -> exam.getDescription().contains(inputSearch)).toList();
            exams.stream().filter(Exam::isStatus).forEach(Exam::displayData);
        } else if (CatalogService.catalogList.stream().anyMatch(catalog -> catalog.getCatalogName().contains(inputSearch))) {
            int examId = CatalogService.catalogList.stream()
                    .filter(catalog -> catalog.getCatalogName().equals(inputSearch)).findFirst().orElse(null).getExamId();
//            ExamService.examList.stream().filter(exam -> exam.getExamId() == examId).forEach(Exam::displayData);
            List<Exam> exams = ExamService.examList.stream().filter(exam -> exam.getExamId() == examId).toList();
            exams.stream().filter(Exam::isStatus).forEach(Exam::displayData);

        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }

    }

    @Override
    public void displayPublicExam() {
        System.out.println("List Public Exam");
        ExamService.examList.stream().filter(Exam::isStatus).forEach(Exam::displayData);
    }


    @Override
    public void startExam() {
        ExamService.examList.stream().filter(exam -> exam.isStatus()).forEach(Exam::displayData);
        System.out.println("Input ExamId To Start");

        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        Exam examStart = findExamById(examId);
        if (examStart != null) {
            List<Question> questions = QuestionService.questionList.stream().filter(question -> question.getExamId() == examStart.getExamId()).toList();
            System.out.printf("--------------Exam %-5s ----------------- \n" +
                            "--------------Student: %-5s  ----------------- \n" +
                            "--------------DURATION: %-3s Minutes ----------------- \n", examStart.getDescription()
                    , UserService.userList.stream().filter(user -> user.getUserId() == examStart.getUserId()).findFirst().orElse(null).getUserName(), examStart.getDuration());
            Result result = new Result();
            ResultDetail resultDetail = null;
            byte totalPoint = 0;
            for (int i = 0; i < questions.size(); i++) {
                resultDetail = new ResultDetail();
                questions.get(i).showExamQuestion(i);
                System.out.println("your choice  ");
                int choice = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                if (choice == questions.get(i).getAnswerTrue()) {
                    totalPoint += 1;
                    resultDetail.inputData(result.getResultId(), questions.get(i).getQuestionId(), choice, true);
                    ResultDetailService.resultDetailList.add(resultDetail);
                } else {
                    resultDetail.inputData(result.getResultId(), questions.get(i).getQuestionId(), choice, false);
                    ResultDetailService.resultDetailList.add(resultDetail);
                }

            }
            try {
                result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, (double) (questions.size() / totalPoint) >= 0.5);
            } catch (ArithmeticException e) {
                result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, false);
            }

            ResultService.resultList.add(result);
            IOFile.writeData(IOFile.QUESTION_PATH, QuestionService.questionList);
            IOFile.writeData(IOFile.RESULT_PATH, ResultService.resultList);
            System.out.println("Exam Done");
            IOFile.writeData(IOFile.RESULT_DETAIL_PATH, ResultDetailService.resultDetailList);

        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void seeResultExam() {
        if (ResultService.resultList.stream().anyMatch(result1 -> result1.getUserId() == LoginMenu.user.getUserId())) {
            ResultService.resultList.stream().filter(result -> result.getUserId() == LoginMenu.user.getUserId()).forEach(Result::displayData);
            System.out.println("Input Result Id  You Want To Review.");
            int resultId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            if (ResultService.resultList.stream().anyMatch(result -> result.getResultId() == resultId)) {
                try {
                    ResultDetailService.resultDetailList.stream().filter(resultDetail -> resultDetail.getResultId() == resultId).forEach(ResultDetail::displayData);
                } catch (NullPointerException e) {
                    System.out.println("User havent take this exam.");
                }
            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }


    }

    @Override
    public void displayInfo() {
        LoginMenu.user.displayPerUser();
    }

    @Override
    public void updateInfo() {
        System.out.println("What part you want to update");
        System.out.println("1. UserName");
        System.out.println("2. Phone Number");
        System.out.println("3. Address");
        System.out.println("4. First Name");
        System.out.println("5. Last Name");
        System.out.println("5. Quit");
        byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        switch (choice) {
            case 1:
                updateUsername();
                break;
            case 2:
                updatePhoneNumber();
                break;
            case 3:
                updateAddress();
                break;
            case 4:
                updateFirstName();
                break;
            case 5:
                updateLastName();
                break;
            case 6:

                break;

            default:
                System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
                break;

        }
    }

    private void updateUsername() {
        System.out.println("Input UserName");
        String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        LoginMenu.user.setUserName(userName);
        UserService.userList.set(UserService.userList.indexOf(LoginMenu.user), LoginMenu.user);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void updateLastName() {
        System.out.println("Input Last Name");
        String lastName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        LoginMenu.user.setLastName(lastName);
        UserService.userList.set(UserService.userList.indexOf(LoginMenu.user), LoginMenu.user);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void updateFirstName() {
        System.out.println("Input FirstName");
        String firstName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        LoginMenu.user.setFirstName(firstName);
        UserService.userList.set(UserService.userList.indexOf(LoginMenu.user), LoginMenu.user);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void updatePhoneNumber() {
        System.out.println("Input Phone number");
        String phoneNumber = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        LoginMenu.user.setPhoneNumber(phoneNumber);
        UserService.userList.set(UserService.userList.indexOf(LoginMenu.user), LoginMenu.user);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void updateAddress() {
        System.out.println("Input Address");
        String address = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        LoginMenu.user.setPhoneNumber(address);
        UserService.userList.set(UserService.userList.indexOf(LoginMenu.user), LoginMenu.user);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    @Override
    public void historyTakeExam() {

    }
}
