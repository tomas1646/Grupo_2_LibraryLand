package com.libraryland.services;

import com.libraryland.entities.Genre;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, Long> implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public GenreServiceImpl(BaseRepository<Genre, Long> baseRepository, EntityManager entityManager) {
        super(baseRepository, entityManager);
    }

    public Genre findByName(String name) throws Exception {
        try {
            return genreRepository.findByName(name).get();
        } catch (Exception e) {
            throw new Exception((e.getMessage()));
        }
    }
}
