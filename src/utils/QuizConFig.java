package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class QuizConFig {
    public static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Scanner scanner = new Scanner(System.in);



    public static byte getByte(String regex, String ERROR){
        return Byte.parseByte(inputFromUser(regex, ERROR));
    }

    public static int getInt(String regex, String ERROR){
        return Integer.parseInt(inputFromUser(regex, ERROR));
    }

    public static boolean getBoolean(String regex, String ERROR){
        return Boolean.parseBoolean(inputFromUser(regex, ERROR));
    }

    public static long getLong(String regex, String ERROR){
        return Long.parseLong(inputFromUser(regex, ERROR));
    }



    public static String inputFromUser(String regex,String ERROR){
        while (true){
            String value = scanner.nextLine();
            if(value.matches(regex)){
                return value;
            }else{
                System.out.println(ERROR);
            }

        }
    }

}
