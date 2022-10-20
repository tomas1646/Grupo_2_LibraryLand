package com.libraryland.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.libraryland.entities.User;

public interface UserService extends BaseService<User, Long> {
    
}
