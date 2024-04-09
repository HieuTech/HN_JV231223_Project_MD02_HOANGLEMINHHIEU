package bussiness.impl;

import bussiness.design.ITeacher;
import bussiness.entity.Exam;
import bussiness.entity.User;

public class TeacherService implements ITeacher {
    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void addNewELement() {
        System.out.println("Add New Exam");
        Exam exam = new Exam();
        exam.inputData(true);
        System.out.println("Add New Exam Successfully");

    }

    @Override
    public void editElement() {

    }

    @Override
    public void deleteElement() {

    }
}
