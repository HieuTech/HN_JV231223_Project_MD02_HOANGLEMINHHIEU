package bussiness.entity;

import bussiness.impl.CatalogService;
import bussiness.impl.ExamService;
import bussiness.impl.Question;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exam implements Serializable  {
    private int examId, userId;
    private String description;
    private List<Integer> catalogId;

    private LocalDate  createAt;
    private List<Integer> questionList;
    private long duration;

    private boolean status;

    public Exam() {
    }

    public Exam(int examId, int userId, String description, List<Integer> catalogId, LocalDate createAt, List<Integer> questionList, long duration, boolean status) {
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



    public List<Integer> getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(List<Integer> catalogId) {
        this.catalogId = catalogId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Integer> questionList) {
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

    public void inputData(boolean isAdd){
        if(isAdd){
            this.setExamId(getNewId());
        }

        System.out.println("Input UserID");
        this.setUserId(QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE));
        this.setCatalogId(getInputCatalogId());

        System.out.println("Input Description");
        this.setDescription(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING,ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Duration");
        this.setDuration(QuizConFig.getLong(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Create Date");
        this.setCreateAt(getInputCreateDate());
        System.out.println("Input status");
        this.setStatus(QuizConFig.getBoolean(ErrorAndRegex.REGEX_STATUS, ErrorAndRegex.ERROR_VALUE));


//        System.out.println("Input FirstName");
//        this.setFirstName(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
//        System.out.println("Input LastName");
//        this.setLastName(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
//        System.out.println("Input Address");
//        this.setAddress(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
//        System.out.println("Input FirstName");
//        this.setFirstName(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
    }

//    public List<Integer> getInputQuestionId(){
//        List<Integer> questionIdList = new ArrayList<>();
//        while (true){
//            System.out.println("List Of Questions");
//            CatalogService.catalogList.forEach(Catalog::displayPerCatalog);
//            System.out.println("How many catalogs you want to add");
//            byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
//            if (count > 0 && count < CatalogService.catalogList.size() ) {
//                for (int i = 0; i < count; i++) {
//                    System.out.println("Choose catalogId by index");
//                    int catalogId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
//                    if(CatalogService.catalogList.stream().anyMatch(catalog -> catalog.getCatalogId().equals(catalogId))){
//                        listCateId.add(catalogId);
//                    }else{
//                        System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
//                    }
//                }
//
//            }else{
//                System.out.println("Quantity You Want To Add Out Of Range");
//            }
//            return listCateId;
//        }
//    }

    public List<Integer> getInputCatalogId(){
        List<Integer> listCateId = new ArrayList<>();
       while (true){
           System.out.println("List Of Categories");
           CatalogService.catalogList.forEach(Catalog::displayPerCatalog);
           System.out.println("How many catalogs you want to add");
           byte count = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
           if (count > 0 && count < CatalogService.catalogList.size() ) {
               for (int i = 0; i < count; i++) {
                   System.out.println("Choose catalogId by index");
                   int catalogId = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER,ErrorAndRegex.ERROR_VALUE);
                   if(CatalogService.catalogList.stream().anyMatch(catalog -> catalog.getCatalogId().equals(catalogId))){
                       listCateId.add(catalogId);
                   }else{
                       System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
                   }
               }

           }else{
               System.out.println("Quantity You Want To Add Out Of Range");
           }
           return listCateId;
       }
    }

    public LocalDate getInputCreateDate(){
        while (true){
            String createdDate = QuizConFig.scanner.nextLine();
            try{
                return LocalDate.parse(createdDate, QuizConFig.DTF);
            }catch (DateTimeParseException e){
                System.out.println(ErrorAndRegex.ERROR_LOCALDATE);
            }
        }
    }

    public int getNewId(){
        int idMax = ExamService.examList.stream().map(Exam::getExamId).max(Comparator.naturalOrder()).orElse(0);
        return idMax + 1;
    }
}
