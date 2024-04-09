package bussiness.entity;

import bussiness.impl.CatalogService;
import bussiness.impl.UserService;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;

public class Catalog implements Serializable {
    private String catalogId, catalogName, description;

    public Catalog() {
    }

    public Catalog(String catalogId, String catalogName, String description) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
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

    public void inputData(boolean isAdd) {
        if (isAdd) {
            System.out.println("Input Catalog ID");
            this.setCatalogId(getInputCatalogId());
        }
        System.out.println("Input Catalog Name");
        this.setCatalogName(getInputCatalogName());
        System.out.println("Input Catalog Description");
        this.setDescription(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING,ErrorAndRegex.ERROR_VALUE));
        System.out.println("Input Catalog Done");


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
        System.out.printf("|ID: %-3s | Catalog Name: %-10s | Description : %5s ",
                this.catalogId,this.catalogName,this.description
        );
    }
}
