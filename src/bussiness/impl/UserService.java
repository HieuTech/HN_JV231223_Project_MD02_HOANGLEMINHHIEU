package bussiness.impl;

import bussiness.entity.User;
import utils.IOFile;

import java.util.List;

public class UserService {
    public static List<User> userList;
    static {
        userList = IOFile.readData(IOFile.USER_PATH);
    }







}
