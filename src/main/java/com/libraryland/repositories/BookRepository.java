package com.libraryland.repositories;

import com.libraryland.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {
    @Query(value = "SELECT b FROM Book b WHERE b.title LIKE %:filtro%")
    List<Book> search(String filtro);

    @Query(value = "SELECT b FROM Book b WHERE b.title LIKE %:filtro%")
    Page<Book> search(String filtro, Pageable pageable);

    @Query(value = "SELECT b FROM Book b JOIN b.genres g WHERE g.name = ?1")    
    Page<Book> findByGenreName(String genre, Pageable pageable);
}
