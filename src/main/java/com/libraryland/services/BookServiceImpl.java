package com.libraryland.services;

import com.libraryland.entities.Book;
import com.libraryland.entities.Genre;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book, Long> implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BaseRepository<Book, Long> baseRepository, EntityManager entityManager) {
        super(baseRepository, entityManager);
    }

    
    @Transactional
    public Page<Book> findByGenre(Pageable pageable, String genre) throws Exception{
        try {
            return bookRepository.findByGenreName(genre,pageable);
        } catch (Exception e) {
            throw new Exception((e.getMessage()));
        }
    }

    
    @Transactional
    public Page<Book> findByAuthor(Pageable pageable, String author) throws Exception{
        try {
            return bookRepository.findByAuthorName(author,pageable);
        } catch (Exception e) {
            throw new Exception((e.getMessage()));
        }
    }
}
