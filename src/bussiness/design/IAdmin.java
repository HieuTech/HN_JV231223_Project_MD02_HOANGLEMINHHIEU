package bussiness.design;

import bussiness.entity.Exam;
import bussiness.entity.User;

public interface IAdmin   {
    public void staticalStudentTookExamInMonth();
    public void staticalExamCreatedInMonth();

  public void findExamMostStudentTookInMonth();
  public void findExamWithTheLeastNumberPeopleLookFor();

  public void findExamWithTheMostNumberPeopleLookFor();

    public void findTheMostWrongQuestionInExam();
    public void findTheMostTrueQuestionInExam();

    public void findNumberUserAccountCreatedInMonth();
    public void findNumberTeacherAccountCreatedInMonth();

    public void findStudentWhoGetHighestScore();
    public void findStudentWhoGetLowestScore();



    public User findById(int userId);
    public void displayAllUser();

    public void blockAndUnblockUser();

    public void findUserByName();


}
