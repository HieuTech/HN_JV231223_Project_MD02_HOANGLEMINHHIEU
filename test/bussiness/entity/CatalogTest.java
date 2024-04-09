package bussiness.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import run.Main;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {


    @Test
    @DisplayName("catalog test input")
    public void testInputCatalog(){

        Catalog catalog = new Catalog();
        Catalog catalog1 = new Catalog();

        catalog.setCatalogId("C123");
        catalog.setCatalogName("catalogName");
        catalog.setDescription("catalog desc");

        catalog1.setCatalogId("C123");
        catalog1.setCatalogName("catalogName");
        catalog1.setDescription("catalog desc");
    }

    @Test
    @DisplayName("catalog test input false")
    public void testInputCatalogFalse(){

        Catalog catalog = new Catalog();
        Catalog catalog1 = new Catalog();

        catalog.setCatalogId("C123");
        catalog.setCatalogName("catalogName");
        catalog.setDescription("catalog desc");

        catalog1.setCatalogId("C123");
        catalog1.setCatalogName("catalogName");
        catalog1.setDescription("catalog desc");
    }

}