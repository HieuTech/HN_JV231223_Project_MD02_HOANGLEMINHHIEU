package bussiness.impl;


import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.entity.Question;
import bussiness.entity.User;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.sql.SQLOutput;

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
        IOFile.writeData(IOFile.EXAM_PATH,ExamService.examList);
    }


    @Override
    public void editElement() {
        System.out.println("Input ExamId To Update");
        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
        Exam examUpdate = findExamById(examId);
        if(examUpdate != null){
            menuUpdateExam(examUpdate);
        }else{
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void seeDetailExam(){
        System.out.println("Input ExamId To See Detail");
        int examId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
        Exam examUpdate = findExamById(examId);
        if(examUpdate != null){
//            examUpdate.getQuestionList().forEach(Question::displayData);
            examUpdate.displayData();
        }else{
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    private void menuUpdateExam(Exam examUpdate){

    }

    @Override
    public void deleteElement() {

    }

    @Override
    public Exam findExamById(Integer id) {
        return ExamService.examList.stream().filter(exam -> exam.getExamId() == id).findFirst().orElse(null);
    }
}
