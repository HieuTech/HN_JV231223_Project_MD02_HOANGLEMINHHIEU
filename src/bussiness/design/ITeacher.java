package bussiness.design;

import bussiness.entity.Exam;
import bussiness.entity.User;

public interface ITeacher extends IGeneric<User, Exam, Integer>{

    public Exam findExamById(Integer id);

    public void seeDetailExam();

}
