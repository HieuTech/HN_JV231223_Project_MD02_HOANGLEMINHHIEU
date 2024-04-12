package bussiness.impl;


import bussiness.design.ITeacher;
import bussiness.entity.*;
import run.login.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherService implements ITeacher {
    @Override
    public User findById(Integer id) {
        return UserService.userList.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }


    @Override
    public void addNewELement() {
        System.out.println("Add New Exam");
        Exam exam = new Exam();
        exam.inputData(true);
        System.out.println("Add New Exam Successfully");
        ExamService.examList.add(exam);
        IOFile.writeData(IOFile.EXAM_PATH, ExamService.examList);
    }


    @Override
    public void seeDetailExam() {
        System.out.println("Input ExamId To See Detail");
        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        Exam examUpdate = findExamById(examId);
        if (examUpdate != null) {
            CatalogService.catalogList.stream().filter(catalog -> catalog.getCatalogId().equals(examUpdate.getCatalogId())).forEach(Catalog::displayPerCatalog);
            System.out.println("-------------------------------------------------------------------");
            QuestionService.questionList.stream().filter(question -> question.getExamId() == examId).forEach(Question::displayData);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }


    @Override
    public void startExam() {
        ExamService.examList.forEach(Exam::displayData);
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
            List<ResultDetail> resultDetailList = new ArrayList<>();
            byte totalPoint = 0;
            for (int i = 0; i < questions.size(); i++) {
                resultDetail = new ResultDetail();
                questions.get(i).showExamQuestion(i);
                System.out.println("your choice  ");
                int choice = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                if (choice == questions.get(i).getAnswerTrue()) {
                    totalPoint += 1;
                    resultDetail.inputData(result.getResultId(), i, choice, true);
                    resultDetailList.add(resultDetail);
                } else {
                    resultDetail.inputData(result.getResultId(), i, choice, false);
                    resultDetailList.add(resultDetail);
                }


            }
            try {
                if ((float) (questions.size() / totalPoint) >= ErrorAndRegex.RANK_EXCELLENT_SCORE) {
                    result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, ErrorAndRegex.RANK_EXCELLENT);
                } else if ((float) (questions.size() / totalPoint) >= ErrorAndRegex.RANK_FAIR_SCORE && (float) (questions.size() / totalPoint) <= ErrorAndRegex.RANK_EXCELLENT_SCORE) {
                    result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, ErrorAndRegex.RANK_FAIR);
                } else {
                    result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, ErrorAndRegex.RANK_AVERAGE);
                }
            } catch (ArithmeticException e) {
                result.inputData(LoginMenu.user.getUserId(), examId, totalPoint, ErrorAndRegex.RANK_AVERAGE);
            }

            result.displayData();
            resultDetailList.forEach(ResultDetail::displayData);
            System.out.println("---------------TEST__EXAM__DONE---------------------");
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }


    @Override
    public void numericUserTakeExam() {
        System.out.printf("Total Member Take Exam: %s \n", ResultService.resultList.size());
        ResultService.resultList.forEach(Result::displayData);
    }


    @Override
    public void editElement() {
        ExamService.examList.forEach(Exam::displayData);
        System.out.println("Input ExamId To Update");
        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        Exam examUpdate = findExamById(examId);
        if (examUpdate != null) {
            menuUpdateExam(examId, examUpdate);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void updateInfo() {
        System.out.println("What part you want to update");
        System.out.println("1. UserName");
        System.out.println("2. Phone Number");
        System.out.println("3. Address");
        System.out.println("4. First Name");
        System.out.println("5. Last Name");
        System.out.println("6. Quit");
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

    @Override
    public void displayInfo() {
        LoginMenu.user.displayPerUser();
    }

    private void updateUsername() {
        System.out.println("Input UserName");
        String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_USERNAME, ErrorAndRegex.ERROR_VALUE);
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
        String phoneNumber = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_PHONE, ErrorAndRegex.ERROR_VALUE);
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

    private void menuUpdateExam(int examId, Exam examUpdate) {
        CatalogService.catalogList.stream().filter(catalog -> catalog.getExamId() == examId).forEach(Catalog::displayPerCatalog);
        System.out.println("-------------------------------------------------------------------");
        QuestionService.questionList.stream().filter(question -> question.getExamId() == examId).forEach(Question::displayData);

        while (true) {
            System.out.println("What part you want to update ");
            System.out.println("1. Catalog ");
            System.out.println("2. Question ");
            System.out.println("3. Exam Description ");
            System.out.println("4. Exam Duration ");
            System.out.println("5. Quit");
            boolean isQuit = false;
            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
                    editCatalog(examId);
                    break;

                case 2:
                    editQuestion(examId);
                    break;
                case 3:
                    editDescription(examUpdate);

                    break;
                case 4:
                    editDuration(examUpdate);

                    break;
                case 5:
                    isQuit = true;
                    break;

                default:
                    System.out.println("Your choice out of range");
                    break;
            }
            if (isQuit) {
                break;
            }
        }

    }

    private void editDescription(Exam exam) {
        System.out.println("Input Description Exam Update");
        String descriptionUpdate = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        exam.setDescription(descriptionUpdate);
        ExamService.examList.set(ExamService.examList.indexOf(exam), exam);

        IOFile.writeData(IOFile.EXAM_PATH, ExamService.examList);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void editDuration(Exam exam) {
        System.out.println("Input Duration Exam Update");
        long newDuration = QuizConFig.getLong(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        exam.setDuration(newDuration);
        ExamService.examList.set(ExamService.examList.indexOf(exam), exam);

        IOFile.writeData(IOFile.EXAM_PATH, ExamService.examList);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void editCatalog(int examId) {
        System.out.println("Input CatalogID you want to update");
        String catalogId = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);

        Catalog catalog = CatalogService.catalogList.stream().filter(catalog1 -> catalog1.getCatalogId().equals(catalogId)).findFirst().orElse(null);

        if (catalog != null) {
            catalog.inputData(false, examId);
            CatalogService.catalogList.set(CatalogService.catalogList.indexOf(catalog), catalog);
            System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
            IOFile.writeData(IOFile.CATALOG_PATH, CatalogService.catalogList);
        }
    }

    private void editQuestion(int examId) {
        System.out.println("Input Question ID you want to update");
        int questionId = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

        Question question = QuestionService.questionList.stream().filter(question1 -> question1.getQuestionId() == questionId).findFirst().orElse(null);

        if (question != null) {
            question.inputData(false, examId);
            QuestionService.questionList.set(QuestionService.questionList.indexOf(question), question);
            IOFile.writeData(IOFile.QUESTION_PATH, QuestionService.questionList);
            System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
            return;
        }

        //=========================UPDATE___ANSWER=========================
        System.out.println("Do you want update Answer of this question | 1.YES  | 2.NO");
        byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (choice == 1) {
            AnswerService.answerList.stream().filter(answer -> answer.getQuestionId() == question.getQuestionId()).forEach(Answer::displayData);
            System.out.println("Input answer Id you want to update");
            int answerId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            Answer answer = AnswerService.answerList.stream().filter(answer1 -> answer1.getAnswerId() == answerId).findFirst().orElse(null);
            if (answer != null) {
                updateAnswer(answer, question);

                //=========================UPDATE___ANSWER__TRUE=========================
                updateAnswerTrue(question);

            } else {
                System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_VALUE);
        }
    }

    private void updateAnswer(Answer answer, Question question) {
        answer.inputData(false, question.getQuestionId());
        AnswerService.answerList.set(AnswerService.answerList.indexOf(answer), answer);
        IOFile.writeData(IOFile.ANSWER_PATH, AnswerService.answerList);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }

    private void updateAnswerTrue(Question question) {
        question.getInputAnswerTrue();
        QuestionService.questionList.set(QuestionService.questionList.indexOf(question), question);
        IOFile.writeData(IOFile.QUESTION_PATH, QuestionService.questionList);
        System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
    }


    @Override
    public void deleteElement() {
        ExamService.examList.forEach(Exam::displayData);
        System.out.println("Input ExamId To Delete");
        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        Exam examDelete = findExamById(examId);
        if (examDelete != null) {
            //delete answer, write file
            for (Question question : examDelete.getQuestionList()) {
                for (Answer answer : question.getAnswerList()) {
                    AnswerService.answerList.remove(answer);
                    IOFile.writeData(IOFile.ANSWER_PATH, AnswerService.answerList);
                }
            }

            //delete question, write file
            for (Question question : examDelete.getQuestionList()) {
                QuestionService.questionList.remove(question);
                IOFile.writeData(IOFile.QUESTION_PATH, QuestionService.questionList);
            }
            //delete exam, write file

            ExamService.examList.remove(examDelete);
            IOFile.writeData(IOFile.EXAM_PATH, ExamService.examList);

            System.out.println(ErrorAndRegex.NOTIFY_SUCCESS);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public Exam findExamById(Integer id) {
        return ExamService.examList.stream().filter(exam -> exam.getExamId() == id).findFirst().orElse(null);
    }
}
