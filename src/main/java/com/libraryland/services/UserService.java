package com.libraryland.services;

import com.libraryland.entities.User;

public interface UserService extends BaseService<User, Long> {
    User login(String username, String password) throws RuntimeException;
}
