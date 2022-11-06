package com.libraryland.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.libraryland.entities.Book;
import com.libraryland.entities.Genre;

public interface BookService extends BaseService<Book, Long> {
    public Page<Book> findByGenre(Pageable pageable, String genre) throws Exception;
    public Page<Book> findByAuthor(Pageable pageable, String author) throws Exception;
}
