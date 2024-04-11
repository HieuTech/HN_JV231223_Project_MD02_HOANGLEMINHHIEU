package bussiness.entity;

import bussiness.impl.AnswerService;
import bussiness.impl.ExamService;
import bussiness.impl.QuestionService;
import run.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question implements Serializable {
    private int questionId, examId;

    private String questionContent;


    private List<Answer> answerList = AnswerService.answerList;

    private byte answerTrue;

    public Question() {

    }

    public Question(int questionId, int examId, String questionContent, List<Answer> answerList, byte answerTrue) {
        this.questionId = questionId;
        this.examId = examId;
        this.questionContent = questionContent;
        this.answerList = answerList;
        this.answerTrue = answerTrue;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public void displayData() {
        System.out.println("Question");
        System.out.printf("%s| ID: %d | Question Content: %-20s \n | Right Answer: %-3s %s\n", ErrorAndRegex.ANSI_BLUE, this.questionId, this.questionContent, this.answerTrue, ErrorAndRegex.ANSI_RESET);
        System.out.println("Answer");
        AnswerService.answerList.stream().filter(answer -> answer.getQuestionId() == this.questionId).forEach(Answer::displayData);
        System.out.println("-------------------------------------------------------------------");
    }

    public void showExamQuestion(int index) {
        System.out.printf("Question number %d \n | Question Content: %-20s | \n Answer \n", index + 1, this.questionContent);
        AnswerService.answerList.stream().filter(answer -> answer.getQuestionId() == this.questionId).forEach(Answer::displayData);
        System.out.println("-------------------------------------------------------------------");
    }


    public void inputData(boolean isAdd, int examId) {
        System.out.println("Input Question Content");
        this.setQuestionContent(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));
        if (isAdd) {
            this.setQuestionId(getNewId());
            this.setExamId(examId);
            System.out.println("Input Answer List");
            getInputAnswerList(this.questionId);
            getInputAnswerTrue();
        }
        System.out.println("Input Question Done");
    }


    public void getInputAnswerTrue() {
        while (true) {
            System.out.println("What is correct answer? Choose By Index");
            AnswerService.answerList.stream().filter(answer -> answer.getQuestionId() == this.questionId).forEach(Answer::displayData);
            byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            if (count >= 0 && count <= AnswerService.answerList.size()) {
                this.setAnswerTrue(count);
                break;
            } else {
                System.out.println(ErrorAndRegex.ERROR_OUT_OF_RANGE);
            }
        }
    }

    public void getInputAnswerList(int questionId) {
        System.out.println("How many Answer You want to add ");
        byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        for (int i = 0; i < count; i++) {
            Answer answer = new Answer();
            System.out.printf("Answer number %d \n", i + 1);
            answer.inputData(true, questionId);
//            this.answerList.add(answer);
            AnswerService.answerList.add(answer);
        }
        this.answerList = AnswerService.answerList;
        IOFile.writeData(IOFile.ANSWER_PATH, AnswerService.answerList);

    }


    public int getNewId() {
        int idMax = QuestionService.questionList.stream()
                .map(Question::getQuestionId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return idMax + 1;

    }

}
