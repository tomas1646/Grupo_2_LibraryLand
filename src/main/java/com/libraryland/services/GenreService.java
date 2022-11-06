package com.libraryland.services;

import com.libraryland.entities.Author;
import com.libraryland.entities.Genre;

public interface GenreService extends BaseService<Genre, Long> {
    Genre findByName(String name) throws Exception;
}
