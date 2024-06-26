package bussiness.entity;

import bussiness.impl.UserService;
import data.Env;
import org.mindrot.jbcrypt.BCrypt;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

public class User implements Serializable {
    private int userId;
    private String firstName, lastName, userName, password,
            email, address, phoneNumber;
    private RoleName roleName = RoleName.ROLE_USER;
    private boolean userStatus = true;

    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User(int userId, String firstName, String lastName, String userName, String password, String email, String address, String phoneNumber, RoleName roleName, boolean userStatus, LocalDate createdDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.userStatus = userStatus;
        this.createdDate = createdDate;
    }

    public User() {
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public void inputData(boolean isAdd) {
        if (isAdd) {
            this.setUserId(getNewId());
            this.setRoleName(getInputRole());
            this.setUserStatus(true);
        }
        System.out.println("Input UserName");
        this.setUserName(getInputUserName());
        System.out.println("Input email");
        this.setEmail(getInputEmail());
        System.out.println("Input password");
        this.setPassword(BCrypt.hashpw(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_PASSWORD, ErrorAndRegex.ERROR_VALUE), BCrypt.gensalt(5)));
        System.out.println("Input phone number");
        this.setPhoneNumber(getInputPhoneNumber());
        System.out.println("Input FirstName");
        this.setFirstName(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
        System.out.println("Input LastName");
        this.setLastName(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
        System.out.println("Input Address");
        this.setAddress(QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_EMPTY));
        System.out.println("Input Created Date");
        this.setCreatedDate(getInputCreatedDate());
    }

    private LocalDate getInputCreatedDate(){
        while (true){
            try{
                String inputDate = QuizConFig.scanner.nextLine();
                return LocalDate.parse(inputDate, QuizConFig.DTF);
            }catch (DateTimeParseException e){
                System.out.println(ErrorAndRegex.ERROR_DATETIME);
            }
        }
    }

    public RoleName getInputRole(){
     while (true){
         System.out.println("Your are Student Or Teacher?\n Please choose by index \n 1: Student 2: Teacher");
         int option = QuizConFig.getInt(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);

         if(option == 1){
             return RoleName.ROLE_USER;
         } else if (option == 2) {
             return RoleName.ROLE_TEACHER;
         } else if (option == Env.getKeyAdmin()){
             return RoleName.ROLE_ADMIN;
         }else{
             System.out.println(ErrorAndRegex.ERROR_VALUE);
         }
     }

    }


    private String getInputPhoneNumber() {
        while (true) {
            String phoneNumber = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_PHONE, ErrorAndRegex.ERROR_VALUE);
            if (UserService.userList.stream().noneMatch(user -> user.getPhoneNumber().equals(phoneNumber))) {
                return phoneNumber;
            } else {
                System.out.println(ErrorAndRegex.ERROR_EXIST);
            }
        }
    }

    private String getInputEmail() {
        while (true) {
            String email = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_EMAIL, ErrorAndRegex.ERROR_VALUE);
            if (UserService.userList.stream().noneMatch(user -> user.getEmail().equals(email))) {
                return email;
            } else {
                System.out.println(ErrorAndRegex.ERROR_EXIST);
            }
        }
    }


    private String getInputUserName() {
        while (true) {
            String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_USERNAME, ErrorAndRegex.ERROR_VALUE);
            if (UserService.userList.stream().noneMatch(user -> user.getUserName().equals(userName))) {
                return userName;
            } else {
                System.out.println(ErrorAndRegex.ERROR_EXIST);
            }
        }
    }

    public void displayData() {
        UserService.userList.forEach(User::displayPerUser);
    }

    public void displayPerUser() {
        System.out.printf("%s|ID: %-3s | userName: %-10s | Role : %5s " +
                        "| First Name: %-6s | Last Name: %-6s | PhoneNumber: %-10s | Created Date: %-6s | Status: %-4s | email: %-10s " +
                        "|  password: %-10s %s\n",
               ErrorAndRegex.ANSI_YELLOW, this.userId, this.userName, this.roleName, this.firstName, this.lastName, this.phoneNumber, this.createdDate,
                (this.userStatus ? "ACTIVE" : "INACTIVE"), this.email, this.password, ErrorAndRegex.ANSI_RESET
        );
    }
    public void displayExamMember() {
        System.out.printf("%s|ID: %-3s | userName: %-10s "+
                        "| First Name: %-6s | Last Name: %-6s | email: %-10s %s \n"
                        ,
                ErrorAndRegex.ANSI_YELLOW,this.userId, this.userName, this.firstName, this.lastName
                , this.email,ErrorAndRegex.ANSI_RESET
        );
    }

    private int getNewId() {
        int idMax = UserService.userList.stream().map(User::getUserId).max(Comparator.naturalOrder()).orElse(0);
        return idMax + 1;
    }



}
