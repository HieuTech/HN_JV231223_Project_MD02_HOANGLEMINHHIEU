package bussiness.entity;

import bussiness.impl.UserService;
import utils.ErrorAndRegex;

import java.io.Serializable;

public class ResultDetail implements Serializable {
    private int resultId;
    private int indexQuestion, indexChoice;
    private boolean check;

    public ResultDetail() {
    }

    public ResultDetail(int resultId, byte indexQuestion, int indexChoice, boolean check) {
        this.resultId = resultId;
        this.indexQuestion = indexQuestion;
        this.indexChoice = indexChoice;
        this.check = check;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getIndexQuestion() {
        return indexQuestion;
    }

    public void setIndexQuestion(int indexQuestion) {
        this.indexQuestion = indexQuestion;
    }

    public int getIndexChoice() {
        return indexChoice;
    }

    public void setIndexChoice(int indexChoice) {
        this.indexChoice = indexChoice;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void inputData(int resultId, int indexQuestion, int choice, boolean check) {
        this.setResultId(resultId);
        this.setIndexQuestion(indexQuestion);
        this.setIndexChoice(choice);
        this.setCheck(check);
    }

    public void displayData() {
        System.out.println("---------------------RESULT_DETAIL------------------------ ");
        System.out.printf("%s| ID: %s | Index Question: %s | Index Choice: %s | Check: %-4s |  %s\n",
                ErrorAndRegex.ANSI_GREEN, this.resultId, this.indexQuestion,
                this.indexChoice, this.check ? "Correct" : "Incorrect", ErrorAndRegex.ANSI_RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");
    }
}
