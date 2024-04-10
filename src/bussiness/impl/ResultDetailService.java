package bussiness.impl;

import bussiness.entity.ResultDetail;
import utils.IOFile;

import java.util.List;

public class ResultDetailService {
    public static List<ResultDetail> resultDetailList;
    static {
        resultDetailList = IOFile.readData(IOFile.RESULT_DETAIL_PATH);
    }
}
