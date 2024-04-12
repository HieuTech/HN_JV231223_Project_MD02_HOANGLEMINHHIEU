package run.main;

import bussiness.entity.Answer;
import bussiness.entity.Question;
import bussiness.impl.AnswerService;
import bussiness.impl.QuestionService;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        QuestionService.questionList.forEach(Question::displayData);
        AnswerService.answerList.forEach(Answer::displayData);
    }
}
