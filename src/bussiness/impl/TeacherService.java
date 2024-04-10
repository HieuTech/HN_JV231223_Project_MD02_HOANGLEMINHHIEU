package bussiness.impl;


import bussiness.design.ITeacher;
import bussiness.entity.*;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.println("Catalog");
            CatalogService.catalogList.stream().filter(catalog -> catalog.getExamId() == examId).forEach(Catalog::displayPerCatalog);
            System.out.println("-------------------------------------------------------------------");
            QuestionService.questionList.stream().filter(question -> question.getExamId() == examId).forEach(Question::displayData);

        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
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
            ExamService.examList.remove(examDelete);
            IOFile.writeData(IOFile.EXAM_PATH,ExamService.examList);
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
