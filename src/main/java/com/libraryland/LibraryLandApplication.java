package com.libraryland;

import com.libraryland.entities.Author;
import com.libraryland.entities.Book;
import com.libraryland.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class LibraryLandApplication {

    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("librarylandPU");
        EntityManager em = enf.createEntityManager();

        try {
            em.getTransaction().begin();

            Author author = Author.builder()
                    .fullName("Brandon Sanderson")
                    .description("Brandon Sanderson (Lincoln, Nebraska, 19 de diciembre de 1975) es un escritor estadounidense de literatura fantástica y ciencia ficción.")
                    .build();
            Genre genre = Genre.builder()
                    .name("Fantasía")
                    .build();
            Book book = Book.builder()
                    .name("El camino de los reyes")
                    .synopsis("En Roshar, un mundo de piedra y\n" +
                            "tormentas, extrañas tempestades de increíble potencia barren el rocoso\n" +
                            "territorio de tal manera que han dado forma a una nueva civilización escondida.\n" +
                            "Han pasado siglos desde la caída de las diez órdenes consagradas conocidas como\n" +
                            "los Caballeros Radiantes, pero sus espadas y armaduras aún permanecen.")
                    .publicationYear(2010)
                    .stock(20)
                    .price(6899.99f)
                    .genres(new ArrayList<>())
                    .authors(new ArrayList<>())
                    .build();
            book.getGenres().add(genre);
            book.getAuthors().add(author);

            em.persist(book);

            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        enf.close();
    }

}
