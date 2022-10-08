package com.libraryland.services;

import com.libraryland.entities.User;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }
}
