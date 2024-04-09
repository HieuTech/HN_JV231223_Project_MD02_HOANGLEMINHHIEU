package bussiness.impl;

import bussiness.entity.Catalog;
import utils.IOFile;

import java.util.List;

public class CatalogService {
    public static List<Catalog> catalogList;
    static {
        catalogList = IOFile.readData(IOFile.CATALOG_PATH);
    }
}
