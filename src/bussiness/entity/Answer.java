package bussiness.entity;

import bussiness.impl.AnswerService;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Answer implements Serializable {

    private int answerId, questionId;

    private String answerContent;




    public Answer() {
    }

    public Answer(int answerId, int questionId, String answerContent) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }




    public void displayData(){
        System.out.printf("%s| ID: %d | Answer Content: %-20s %s \n",ErrorAndRegex.ANSI_GREEN, this.answerId, this.answerContent
        ,ErrorAndRegex.ANSI_RESET);
    }


    public void inputData(boolean isAdd, int questionId ){
        if(isAdd){
            this.setAnswerId(getNewId());
            this.setQuestionId(questionId);
        }
        System.out.println("Input Answer Content");
        this.setAnswerContent(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Answer Done");
    }



    public int getNewId(){

        int idMax = AnswerService.answerList.stream()
                .map(Answer::getAnswerId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return idMax + 1;

    }
}
