package com.libraryland.services;

import com.libraryland.entities.User;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }

    public User login(String username, String password) throws RuntimeException {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        if (user.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        return user.get();
    }

}
