package com.libraryland.services;

import com.libraryland.entities.User;
import com.libraryland.entities.UserDetailsWrapper;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User, Long> baseRepository, EntityManager entityManager) {
        super(baseRepository, entityManager);
    }

    @Override
    @Transactional
    public User save(User user) throws Exception {
        try {
            user.setRoles("USER");
            user = userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return user.map(UserDetailsWrapper::new).get();
    }
}
