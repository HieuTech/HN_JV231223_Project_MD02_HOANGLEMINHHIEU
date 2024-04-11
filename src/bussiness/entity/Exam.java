package bussiness.entity;

import bussiness.impl.CatalogService;
import bussiness.impl.ExamService;
import bussiness.impl.QuestionService;
import bussiness.impl.UserService;
import run.LoginMenu;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exam implements Serializable {
    private int examId, userId;
    private String description;
    private List<Catalog> catalogList = CatalogService.catalogList;

    private LocalDate createAt;
    private List<Question> questionList = QuestionService.questionList;
    private long duration;

    private boolean status;

    public Exam() {

    }

    public Exam(int examId, int userId, String description, List<Catalog> catalogList, LocalDate createAt, List<Question> questionList, long duration, boolean status) {
        this.examId = examId;
        this.userId = userId;
        this.description = description;
        this.catalogList = catalogList;
        this.createAt = createAt;
        this.questionList = questionList;
        this.duration = duration;
        this.status = status;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void displayData() {
        String creatorName = UserService.userList.stream().filter(user -> user.getUserId() == this.userId).findFirst().orElse(null).getUserName();
        System.out.println("---------------------EXAM__INFO------------------------ ");
        System.out.printf("%s| ID: %s | Creator: %-5s | Exam Title: %-15s | Status: %-7s | Created_Date: %-10s %s \n", ErrorAndRegex.ANSI_CYAN, this.examId,
                creatorName, this.description, this.status ? "PUBLISH" : "PRIVATE", this.createAt, ErrorAndRegex.ANSI_RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");


    }

    public void inputData(boolean isAdd) {
        if (isAdd) {
            this.setExamId(getNewId());
            this.setUserId(LoginMenu.user.getUserId());
            getInputCatalogList();
            getInputCreateDate();
            getInputQuestion();
        }

        System.out.println("Input Exam Description");
        this.setDescription(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Exam Duration");
        this.setDuration(QuizConFig.getLong(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Exam status");
        this.setStatus(QuizConFig.getBoolean(ErrorAndRegex.REGEX_STATUS, ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Exam done");
    }

    public void getInputQuestion() {

        System.out.println("How many Questions you want to add");
        byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

        for (int i = 0; i < count; i++) {
            Question question = new Question();
            System.out.printf("Question number %d \n ", i + 1);
            question.inputData(true, this.examId);
            System.out.println("Add 1 question done");
            QuestionService.questionList.add(question);
        }
        this.questionList = QuestionService.questionList;
        IOFile.writeData(IOFile.QUESTION_PATH, QuestionService.questionList);

    }


    public void getInputCatalogList() {

        while (true) {
            if (CatalogService.catalogList.isEmpty()) {
                System.out.println(ErrorAndRegex.NOTIFY_EMPTY);
                System.out.println("How many catalog you want to add");
                byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

                for (int i = 0; i < count; i++) {
                    Catalog catalog = new Catalog();
                    catalog.inputData(true, this.examId);
                    CatalogService.catalogList.add(catalog);
                }
                this.catalogList = CatalogService.catalogList;
                IOFile.writeData(IOFile.CATALOG_PATH, CatalogService.catalogList);
                break;
//                return CatalogService.catalogList;
            } else {
                System.out.println("List Of Categories");
                CatalogService.catalogList.forEach(Catalog::displayPerCatalog);
                System.out.println("You want to add more categories your select categories exist \n |1. Add more | 2. Choose Exits ");

                byte select = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                if (select == 1) {
                    System.out.println("How many catalog you want to add");
                    byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

                    for (int i = 0; i < count; i++) {
                        Catalog catalog = new Catalog();
                        catalog.inputData(true, this.examId);
                        CatalogService.catalogList.add(catalog);
                    }
                    this.catalogList = CatalogService.catalogList;
                    IOFile.writeData(IOFile.CATALOG_PATH, CatalogService.catalogList);
//                    return CatalogService.catalogList;
                } else if (select == 2) {
                    while (true) {
                        System.out.println("Choose catalogId");
                        String catalogIdChoose = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_CATALOG_ID, ErrorAndRegex.ERROR_VALUE);
                        if (CatalogService.catalogList.stream().anyMatch(catalog -> catalog.getCatalogId().equals(catalogIdChoose))) {
                            this.catalogList = CatalogService.catalogList.stream().filter(catalog -> catalog.getCatalogId().equals(catalogIdChoose)).toList();
//                                IOFile.writeData(IOFile.CATALOG_PATH, CatalogService.catalogList);
                            return;
//                                return CatalogService.catalogList.stream().filter(catalog -> catalog.getCatalogId().equals(catalogIdChoose)).toList();
                        } else {
                            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
                        }
                    }


                } else {
                    System.out.println(ErrorAndRegex.ERROR_VALUE);

                }
            }

        }
    }

    public void getInputCreateDate() {
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.format(QuizConFig.DTF);
        LocalDate parsedDate = LocalDate.parse(formattedDate, QuizConFig.DTF);
        this.setCreateAt(parsedDate);
    }

    public int getNewId() {
        int idMax = ExamService.examList.stream().map(Exam::getExamId).max(Comparator.naturalOrder()).orElse(0);
        return idMax + 1;
    }
}
