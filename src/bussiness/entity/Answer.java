package bussiness.entity;

import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Answer implements Serializable {

    private int answerId;

    private String answerContent;




    public Answer() {
    }

    public Answer(int answerId, String answerContent) {
        this.answerId = answerId;
        this.answerContent = answerContent;

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
        System.out.printf("| ID: %d | Content: %-20s \n", this.answerId, this.answerContent);
    }


    public void inputData(boolean isAdd, List<Answer> answerList){
        if(isAdd){
            this.setAnswerId(getNewId(answerList));
        }
        System.out.println("Input Answer Content");
        this.setAnswerContent(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));

        System.out.println("Input Answer Done");

    }



    public int getNewId(List<Answer> answerList){

        int idMax = answerList.stream()
                .map(Answer::getAnswerId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return idMax + 1;

    }
}
