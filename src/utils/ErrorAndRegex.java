package utils;

public class ErrorAndRegex {

    public static final String REGEX_NUMBER= "\\d+";
    public static final String REGEX_STRING = "\\w+";
    public static final String REGEX_USERNAME = "\\w{6,100}";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String REGEX_PHONE = "(84)\\d{9}";
    public static final String REGEX_STATUS = "(true|false)";

    public static final String REGEX_PASSWORD = "\\w{8,100}";
    public static final String REGEX_CATALOG_ID = "C\\w{3}";
    public static final String ERROR_BLOCK_ACCOUNT= "Your account is banned, please contact admin(vegan@gmail.com)";


    public static final String ERROR_LOCALDATE= "Input Wrong Format dd/MM/yyyy";
    public static final String ERROR_VALUE= "Input invalid";
    public static final String ERROR_EMPTY = "Input Is Not Be Empty";
    public static final String ERROR_EXIST = "Value Is Exist";
    public static final String ERROR_OUT_OF_RANGE = "Value Out Of Range";

    public static final String ERROR_NOT_FOUND = "Value Not Found";

    public static final String NOTIFY_EMPTY= "List Is Empty";
    public static final String NOTIFY_SUCCESS= "Successfully";


}
