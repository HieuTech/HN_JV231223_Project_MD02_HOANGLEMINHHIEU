package bussiness.entity;

import bussiness.impl.ExamService;
import run.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question implements Serializable {
    private int questionId;
    private String questionContent;

    private List<Answer> answerList;

    private byte answerTrue;

    public Question() {
        answerList = new ArrayList<>();
    }

    public Question(int questionId, String questionContent, List<Answer> answerList, byte answerTrue) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.answerList = answerList;
        this.answerTrue = answerTrue;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }



    public byte getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(byte answerTrue) {
        this.answerTrue = answerTrue;
    }

    public void displayData(){
        System.out.printf("| ID: %d | Content: %-20s \n", this.questionId, this.questionContent);
        answerList.forEach(Answer::displayData);
    }

    public void inputData(boolean isAdd, List<Question> questionList ){
        if(isAdd){
            this.setQuestionId(getNewId(questionList));
        }
        System.out.println("Input Question Content");
        this.setQuestionContent(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Answer List");
        getInputAnswerList();
        getInputAnswerTrue();
        System.out.println("Input Question Done");

    }


    public void getInputAnswerTrue(){
       while (true){
           System.out.println("What is correct answer? Choose By Index");
           answerList.forEach(Answer::displayData);
           byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
           if(count >= 0 && count <= answerList.size()){
               this.setAnswerTrue(count);
               break;
           }else{
               System.out.println(ErrorAndRegex.ERROR_OUT_OF_RANGE);
           }
       }
    }

    public void getInputAnswerList(){
        System.out.println("How many Answer You want to add ");
        byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
        for (int i = 0; i < count; i++) {
            Answer answer = new Answer();
            System.out.printf("Answer number %d \n", i+1);
            answer.inputData(true, this.answerList);
            this.answerList.add(answer);
        }
        IOFile.writeData(IOFile.EXAM_PATH,answerList);

    }






    public int getNewId(List<Question> questionList){
        int idMax = questionList.stream()
                .map(Question::getQuestionId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return idMax + 1;

    }

}
