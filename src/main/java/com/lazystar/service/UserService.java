package com.lazystar.service;

import com.lazystar.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
