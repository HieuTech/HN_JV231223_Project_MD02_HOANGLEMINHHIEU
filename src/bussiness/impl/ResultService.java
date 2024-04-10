package bussiness.impl;

import bussiness.entity.Result;
import utils.IOFile;

import java.util.List;

public class ResultService {
    public static List<Result> resultList;
    static {
        resultList = IOFile.readData(IOFile.RESULT_PATH);
    }
}
