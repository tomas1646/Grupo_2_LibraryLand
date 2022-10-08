package com.libraryland.services;

import com.libraryland.entities.Genre;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, Long> implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public GenreServiceImpl(BaseRepository<Genre, Long> baseRepository) {
        super(baseRepository);
    }
}
