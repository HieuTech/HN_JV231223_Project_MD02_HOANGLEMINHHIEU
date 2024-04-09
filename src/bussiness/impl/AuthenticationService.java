package bussiness.impl;

import bussiness.design.IAuthentication;
import bussiness.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import utils.IOFile;

public class AuthenticationService implements IAuthentication {

    @Override
    public User login(String userName, String password) {
        User userLogin = getUserFromUserName(userName);
        if(userLogin != null){
            boolean checkLogin = BCrypt.checkpw(password, userLogin.getPassword());
            if(checkLogin){
                return userLogin;
            }
        }
        return null;
    }

    @Override
    public void register(User user) {
            user.inputData(true);
            UserService.userList.add(user);
        IOFile.writeData(IOFile.USER_PATH,UserService.userList);
    }

    private User getUserFromUserName(String userName){
        return UserService.userList.stream().filter(user -> user.getUserName().equals(userName)).findFirst().orElse(null);
    }



}
