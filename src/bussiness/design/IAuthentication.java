package bussiness.design;

import bussiness.entity.User;

public interface IAuthentication {
    User login(String userName, String password);
    void register(User user);
}
