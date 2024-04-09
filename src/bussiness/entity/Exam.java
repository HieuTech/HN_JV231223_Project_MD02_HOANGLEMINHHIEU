package bussiness.entity;

import bussiness.impl.CatalogService;
import bussiness.impl.ExamService;
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
    private List<String> catalogId;

    private LocalDate createAt;
    private List<Question> questionList;
    private long duration;

    private boolean status;

    public Exam() {
        questionList = new ArrayList<>();
    }

    public Exam(int examId, int userId, String description, List<String> catalogId, LocalDate createAt, List<Question> questionList, long duration, boolean status) {
        this.examId = examId;
        this.userId = userId;
        this.description = description;
        this.catalogId = catalogId;
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

    public List<String> getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(List<String> catalogId) {
        this.catalogId = catalogId;
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
        System.out.printf("| ID: %s | Creator: %-4s | Desc: %-15s | Status: %-7s | Created_Date: %-10s \n", this.examId,
                this.userId, this.description, this.status ? "PUBLISH" : "PRIVATE", this.createAt);
        questionList.forEach(Question::displayData);

    }

    public void inputData(boolean isAdd) {
        if (isAdd) {
            this.setExamId(getNewId());
        }

        this.setUserId(LoginMenu.user.getUserId());
        this.setCatalogId(getInputCatalogId());
        System.out.println("Input Exam Description");
        this.setDescription(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE));
        getInputQuestion();
        System.out.println("Input Exam Create Date");
        this.setCreateAt(getInputCreateDate());
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
            question.inputData(true, questionList);
            System.out.println("Add 1 question done");
        }
        IOFile.writeData(IOFile.EXAM_PATH,questionList);

    }


    public List<String> getInputCatalogId() {
        List<String> listCateId = new ArrayList<>();
        while (true) {

            System.out.println("List Of Categories");
            CatalogService.catalogList.forEach(Catalog::displayPerCatalog);
            System.out.println("You want to add more categories your select categories exist \n |1. Add more | 2. Choose Exits ");

            byte select = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            if (select == 1) {
                System.out.println("How many catalog you want to add");
                byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

                for (int i = 0; i < count; i++) {
                    Catalog catalog = new Catalog();
                    catalog.inputData(true);
                    CatalogService.catalogList.add(catalog);
                }
                IOFile.writeData(IOFile.CATALOG_PATH,CatalogService.catalogList);

                break;

            } else if (select == 2) {
                if (CatalogService.catalogList.isEmpty()) {
                    System.out.println(ErrorAndRegex.NOTIFY_EMPTY);

                } else {
                    System.out.println("How many catalog you want to choose");
                    byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                    if (count > 0 && count < CatalogService.catalogList.size()) {
                        for (int i = 0; i < count; i++) {
                            System.out.println("Choose catalogId by index");
                            String catalogId = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
                            if (CatalogService.catalogList.stream().anyMatch(catalog -> catalog.getCatalogId().equals(catalogId))) {
                                listCateId.add(catalogId);
                                IOFile.writeData(IOFile.CATALOG_PATH,CatalogService.catalogList);
                            } else {
                                System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
                            }
                        }
                    } else {
                        System.out.println("Quantity You Want To Add Out Of Range");
                    }
                }

            } else {
                System.out.println(ErrorAndRegex.ERROR_VALUE);

            }

        }

        return listCateId;

    }

    public LocalDate getInputCreateDate() {
        while (true) {
            String createdDate = QuizConFig.scanner.nextLine();
            try {
                return LocalDate.parse(createdDate, QuizConFig.DTF);
            } catch (DateTimeParseException e) {
                System.out.println(ErrorAndRegex.ERROR_LOCALDATE);
            }
        }
    }

    public int getNewId() {
        int idMax = ExamService.examList.stream().map(Exam::getExamId).max(Comparator.naturalOrder()).orElse(0);
        return idMax + 1;
    }
}
