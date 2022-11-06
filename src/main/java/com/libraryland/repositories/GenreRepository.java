package com.libraryland.repositories;

import com.libraryland.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {
    @Query(value = "SELECT g FROM Genre g WHERE g.name LIKE %:filtro%")
    List<Genre> search(String filtro);

    @Query(value = "SELECT g FROM Genre g WHERE g.name LIKE %:filtro%")
    Page<Genre> search(String filtro, Pageable pageable);

    Optional<Genre> findByName(String name);
}
