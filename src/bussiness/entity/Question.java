package bussiness.entity;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private int questionId;
    private String questionContent;

    private List<Integer> answerId;

    private byte answerTrue;

    public Question() {
    }

    public Question(int questionId, String questionContent, List<Integer> answerId, byte answerTrue) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.answerId = answerId;
        this.answerTrue = answerTrue;
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

    public List<Integer> getAnswerId() {
        return answerId;
    }

    public void setAnswerId(List<Integer> answerId) {
        this.answerId = answerId;
    }

    public byte getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(byte answerTrue) {
        this.answerTrue = answerTrue;
    }
}
