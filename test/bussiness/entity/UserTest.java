package bussiness.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {


    @Test
    @DisplayName("catalog test input")
    public void testInputUser(){

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
    }

    @Test
    @DisplayName("catalog test input false")
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