package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IOFile {
    public static final String ANSWER_PATH = "src/data/answer.txt";
    public static final String USER_PATH = "src/data/user.txt";
    public static final String EXAM_PATH = "src/data/exam.txt";
    public static final String QUESTION_PATH = "src/data/question.txt";
    public static final String RESULT_PATH = "src/data/result.txt";
    public static final String CATALOG_PATH = "src/data/catalog.txt";
    public static final String USER_LOGIN_PATH = "src/data/userLogin.txt";
    public static final String RESULT_DETAIL_PATH = "src/data/resultDetail.txt";


    public static <T> void writeData(String path, List<T> list){
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        }catch (IOException e) {

        }finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void writePerObject(String path, T objects){
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            System.out.println("Write successfully");
        }catch (IOException e) {

        }finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static <T> T readPerData(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        T objects = null;
        try{

            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            objects = (T) ois.readObject();

        }catch (EOFException e){

        }catch (IOException e){
            System.out.println("IO Exception");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }


    public static <T> List<T> readData(String path){
        List<T> list = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{

            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();

        }catch (EOFException e){

        }catch (IOException e){
            System.out.println("IO Exception");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;

    }
}
