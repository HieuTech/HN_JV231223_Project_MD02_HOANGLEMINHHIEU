package bussiness.entity;

import bussiness.impl.AnswerService;
import bussiness.impl.QuestionService;
import bussiness.impl.UserService;
import utils.ErrorAndRegex;

import java.io.Serializable;
import java.util.List;

public class ResultDetail implements Serializable {
    private int resultId;
    private int questionId, answerId;
    private boolean check;

    public ResultDetail() {
    }

    public ResultDetail(int resultId, byte questionId, int answerId, boolean check) {
        this.resultId = resultId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.check = check;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getIndexQuestion() {
        return questionId;
    }

    public void setIndexQuestion(int questionId) {
        this.questionId = questionId;
    }

    public int getIndexChoice() {
        return answerId;
    }

    public void setIndexChoice(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void inputData(int resultId, int questionId, int choice, boolean check) {
        this.setResultId(resultId);
        this.setIndexQuestion(questionId);
        this.setIndexChoice(choice);
        this.setCheck(check);
    }

    public void displayData() {
        String questionContent = QuestionService.questionList.stream().filter(question -> question.getQuestionId() == this.questionId).findFirst().orElse(null).getQuestionContent();
        String answerContent =  AnswerService.answerList.stream().filter(answer -> answer.getAnswerId() == this.answerId).findFirst().orElse(null).getAnswerContent();
        System.out.println("---------------------RESULT_DETAIL------------------------ ");
        System.out.printf("%s| ID: %s |  Question: %s | Your Answer: %s | Result: %-4s |  %s\n",
                ErrorAndRegex.ANSI_GREEN, this.resultId, questionContent,
                answerContent, this.check ? "Correct" : "Incorrect", ErrorAndRegex.ANSI_RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public void displayDataForTeacher(List<Question> questions){
        String questionContent = questions.stream().filter(question -> question.getQuestionId() == this.questionId).findFirst().orElse(null).getQuestionContent();
            String answerContent =  AnswerService.answerList.stream().filter(answer -> answer.getAnswerId() == this.answerId).findFirst().orElse(null).getAnswerContent();
        System.out.println("---------------------RESULT_DETAIL------------------------ ");
        System.out.printf("%s| ID: %s |  Question: %s | Your Answer: %s | Result: %-4s |  %s\n",
                ErrorAndRegex.ANSI_GREEN, this.resultId, questionContent,
                answerContent, this.check ? "Correct" : "Incorrect", ErrorAndRegex.ANSI_RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");
    }
}
