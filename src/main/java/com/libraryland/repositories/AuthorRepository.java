package com.libraryland.repositories;

import com.libraryland.entities.Author;
import com.libraryland.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends BaseRepository<Author, Long> {
    @Query(value = "SELECT a FROM Author a WHERE a.fullName LIKE %:filtro%")
    List<Author> search(String filtro);

    @Query(value = "SELECT a FROM Author a WHERE a.fullName LIKE %:filtro%")
    Page<Author> search(String filtro, Pageable pageable);
}
