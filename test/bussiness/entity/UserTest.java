package bussiness.entity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.IOFile;

import java.util.List;

class UserTest {


    @Test
    @DisplayName("catalog test input")
    public void testInputUser(){

        List<User> userList = IOFile.readData(IOFile.USER_PATH);

        User user = new User();
        user.setUserId(1);
        user.setUserName("userName");
        user.setFirstName("first name");
        user.setAddress("address");
        user.setPhoneNumber("+84375075123");
        user.setEmail("email@gmail.com");
        user.setPassword("minhhieu123");
        user.setLastName("catalog desc");
        user.setRoleName(RoleName.ROLE_USER);
        user.setUserStatus(true);
        userList.add(user);
        IOFile.writeData(IOFile.USER_PATH,userList);
    }

    @Test
    @DisplayName("catalog test input false")
    @Disabled
    public void testInputUserFalse(){


        User user = new User();
        user.setUserId(1);
        user.setUserName("");
        user.setFirstName("");
        user.setAddress("address");
        user.setPhoneNumber("+075123");
        user.setEmail("emailil.com");
        user.setPassword("minh123");
        user.setLastName("");
        user.setRoleName(RoleName.ROLE_USER);
        user.setUserStatus(false);
    }

}