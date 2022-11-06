package com.libraryland.services;

import com.libraryland.entities.Author;

public interface AuthorService extends BaseService<Author, Long> {
    Author findByFullName(String fullName) throws Exception;
}
