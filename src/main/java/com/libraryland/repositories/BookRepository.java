package com.libraryland.repositories;

import com.libraryland.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseRepository<Book,Long> {
}
