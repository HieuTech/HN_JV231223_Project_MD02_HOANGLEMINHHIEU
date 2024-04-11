package utils;

public class ErrorAndRegex {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public static final String REGEX_NUMBER= "\\d+";
    public static final String REGEX_STRING = "^[a-z0-9\\s+]{1,50}$";
    public static final String REGEX_USERNAME = "\\w{6,100}";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String REGEX_PHONE = "(84)\\d{9}";
    public static final String REGEX_STATUS = "(true|false)";
    public static final String REGEX_PASSWORD = "\\w{8,100}";
    public static final String REGEX_CATALOG_ID = "C\\w{3}";
    public static final String ERROR_BLOCK_ACCOUNT= "Your account is banned, please contact admin(vegan@gmail.com)";
    public static final String ERROR_VALUE= "Input invalid";
    public static final String ERROR_EMPTY = "Input Is Not Be Empty";
    public static final String ERROR_EXIST = "Value Is Exist";
    public static final String ERROR_OUT_OF_RANGE = "Value Out Of Range";
    public static final String ERROR_NOT_FOUND = "Value Not Found";
    public static final String NOTIFY_EMPTY= "List Is Empty";
    public static final String NOTIFY_SUCCESS= "Successfully";


}
