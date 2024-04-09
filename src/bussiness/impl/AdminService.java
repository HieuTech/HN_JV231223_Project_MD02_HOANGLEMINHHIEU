package bussiness.impl;

import bussiness.design.IAdmin;
import bussiness.entity.RoleName;
import bussiness.entity.User;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.util.Optional;

public class AdminService implements IAdmin {


    @Override
    public void displayAllUser() {
        if(UserService.userList.isEmpty()){
            System.out.println(ErrorAndRegex.NOTIFY_EMPTY);
        }else{
            UserService.userList.forEach(User::displayPerUser);
        }
    }

    @Override
    public User findById(Integer id) {
        return UserService.userList.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    @Override
    public void addNewELement() {

    }

    @Override
    public void editElement() {

    }

    @Override
    public void deleteElement() {

    }


    @Override
    public void blockAndUnblockUser() {
        displayAllUser();
        System.out.println("Input UserId You Want To Change Status");
        int userId = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        User user = findById(userId);
        if (user != null){
            user.setUserStatus(!user.isUserStatus());
            System.out.println("Change Successfully");
        }else{
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }


    }


    @Override
    public void findUserByName() {
        System.out.println("Input User Name you want to search");
        String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        UserService.userList.stream().filter(user -> user.getUserName().contains(userName)).forEach(User::displayPerUser);
    }

    @Override
    public void staticalStudentList() {
        System.out.println("Total Student List");
        UserService.userList.stream().filter(user -> user.getRoleName().equals(RoleName.ROLE_USER)).forEach(User::displayPerUser);
    }


    @Override
    public void staticalExam() {

    }

    @Override
    public void staticalAverageScoreInMonth() {

    }

    @Override
    public void staticalStudentsHighestScoreInMonth() {

    }


}
