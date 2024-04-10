package bussiness.entity;

import bussiness.impl.CatalogService;
import bussiness.impl.UserService;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.util.List;

public class Catalog implements Serializable {
    private String catalogId, catalogName, description;

    private int examId;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Catalog() {
    }

    public Catalog(String catalogId, String catalogName, String description, int examId) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.examId = examId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void inputData(boolean isAdd, int examId) {
        if (isAdd) {
            System.out.println("Input Catalog ID");
            this.setCatalogId(getInputCatalogId());
            this.setExamId(examId);
        }
        System.out.println("Input Catalog Name");
        this.setCatalogName(getInputCatalogName());
        System.out.println("Input Catalog Description");
        this.setDescription(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING,ErrorAndRegex.ERROR_VALUE));
    }

    public String getInputCatalogName(){
        while (true){
            String catalogName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING,ErrorAndRegex.ERROR_VALUE);
            if(CatalogService.catalogList.stream().noneMatch(catalog -> catalog.getCatalogName().equals(catalogName))){
                return catalogName;
            }
            else {
                System.out.println(ErrorAndRegex.ERROR_EXIST);
            }
        }
    }

    public String getInputCatalogId(){
        while (true){
            String catalogId = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_CATALOG_ID,ErrorAndRegex.ERROR_VALUE);
            if(CatalogService.catalogList.stream().noneMatch(catalog -> catalog.getCatalogId().equals(catalogId))){
                return catalogId;
            }
            else {
                System.out.println(ErrorAndRegex.ERROR_EXIST);
            }
        }
    }



    public void displayData(){
        CatalogService.catalogList.forEach(Catalog::displayPerCatalog);
    }

    public void displayPerCatalog(){
        System.out.println("Catalog");
        System.out.printf("%s|ID: %-3s | Catalog Name: %-10s | Description : %5s %s\n",
               ErrorAndRegex.ANSI_YELLOW, this.catalogId,this.catalogName,this.description,ErrorAndRegex.ANSI_RESET
        );
        System.out.println("-------------------------------------------------------------------");

    }
}
