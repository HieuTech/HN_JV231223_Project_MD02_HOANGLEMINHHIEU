package bussiness.design;

import bussiness.entity.Exam;
import bussiness.entity.User;

public interface IAdmin  extends IGeneric<User, Exam, Integer>  {
    public void displayAllUser();

    public void blockAndUnblockUser();

    public void findUserByName();

    public void staticalStudentList();
    public void staticalExam();

    public void staticalAverageScoreInMonth();

    public void staticalStudentsHighestScoreInMonth();
}
