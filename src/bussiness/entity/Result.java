package bussiness.entity;

import bussiness.impl.ResultService;
import bussiness.impl.UserService;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Result implements Serializable {


    private int resultId, userId, examId, totalPoint;
    private LocalDate createdDate;
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Result() {
        this.setResultId(getNewId());
    }

    public Result(int resultId, int userId, int examId, int totalPoint, LocalDate createdDate, boolean result) {
        this.resultId = resultId;
        this.userId = userId;
        this.examId = examId;
        this.totalPoint = totalPoint;
        this.createdDate = createdDate;
        this.result = result;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void displayData(){
        System.out.println("---------------------RESULT------------------------ ");
        System.out.printf("%s| ID: %s | ExamID: %-4s | UserName: %-10s | Total Point: %s | Result: %-4s | Created_Date: %-10s %s\n",
                ErrorAndRegex.ANSI_PURPLE, this.resultId, this.examId,
                UserService.userList.stream().filter(user -> user.getUserId() == this.userId).findFirst().orElse(null).getUserName(),
                this.totalPoint, (this.result ? "Passed" : "False"), this.createdDate, ErrorAndRegex.ANSI_RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");

    }
    public void inputData(int userId, int examId, int totalPoint, boolean result) {

        setCreatedDate();
        this.setUserId(userId);
        this.setExamId(examId);
        this.setTotalPoint(totalPoint);
        this.setResult(result);

    }

    private void setCreatedDate() {
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.format(QuizConFig.DTF);

// Sử dụng cùng định dạng khi phân tích cú pháp
        LocalDate parsedDate = LocalDate.parse(formattedDate, QuizConFig.DTF);
        this.setCreatedDate(parsedDate);
    }


    public int getNewId() {
        int idMax = ResultService.resultList.stream().map(Result::getResultId).max(Comparator.naturalOrder()).orElse(0);
        return idMax + 1;
    }
}
