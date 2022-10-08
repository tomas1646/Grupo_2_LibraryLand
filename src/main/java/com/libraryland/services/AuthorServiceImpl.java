package com.libraryland.services;

import com.libraryland.entities.Author;
import com.libraryland.repositories.AuthorRepository;
import com.libraryland.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<Author, Long> implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(BaseRepository<Author, Long> baseRepository) {
        super(baseRepository);
    }
}
