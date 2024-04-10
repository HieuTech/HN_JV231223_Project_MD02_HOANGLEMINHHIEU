package bussiness.design;

import bussiness.entity.Exam;

public interface IUser {
    public Exam findExamById(Integer id);


    public void changePassword();
    public void findExamByName();

    public void displayPublicExam();

    public void startExam();

    public void seeResultExam();
    public void displayInfo();
    public void updateInfo();

    public void historyTakeExam();

}
