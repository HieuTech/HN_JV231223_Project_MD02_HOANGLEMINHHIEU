package bussiness.impl;

import bussiness.entity.Exam;
import utils.IOFile;

import java.util.List;

public class Question {
    public static List<Question> questionList;
    static {
        questionList = IOFile.readData(IOFile.QUESTION_PATH);
    }
}
