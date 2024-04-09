package bussiness.impl;

import bussiness.entity.Exam;
import utils.IOFile;

import java.util.List;

public class ExamService {
    public static List<Exam> examList;
    static {
        examList = IOFile.readData(IOFile.EXAM_PATH);
    }
}
