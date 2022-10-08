package com.libraryland.repositories;

import com.libraryland.entities.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends BaseRepository<Genre,Long> {
}
