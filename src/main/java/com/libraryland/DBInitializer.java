package com.libraryland;

import com.libraryland.entities.*;
import com.libraryland.services.AuthorService;
import com.libraryland.services.BookService;
import com.libraryland.services.GenreService;
import com.libraryland.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitializer {
    private final GenreService genreService;
    private final AuthorService authorService;
    private final UserService userService;
    private final BookService bookService;

    public DBInitializer(GenreService genreService, AuthorService authorService, UserService userService, BookService bookService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostConstruct
    private void initializeDB() {
        // Initialize tasks
        Genre genre1 = createGenre("Fiction", 1L);
        createGenre("Non-Fiction", 2L);
        createGenre("Sciencie Fiction", 3L);
        createGenre("Fantasy", 4L);

        Author author = createAuthor("J. K. Rowling", "Joanne Rowling, better known by her pen name J. K. Rowling, is a British author, philanthropist, film producer, television producer, and screenwriter. She is best known for writing the Harry Potter fantasy series, which has won multiple awards and sold more than 500 million copies, becoming the best-selling book series in history. The books are the basis of a popular film series, over which Rowling had overall approval on the scripts and was a producer on the final films. She also writes crime fiction under the pen name Robert Galbraith.", 1L);
        createAuthor("Stephen King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, and fantasy novels. His books have sold more than 350 million copies, and many have been adapted into films, television series, miniseries, and comic books. King has published 54 novels, including seven under the pen name Richard Bachman and six non-fiction books. He has written nearly 200 short stories, most of which have been collected in book collections. Many of his stories are set in his home state of Maine.", 2L);
        createAuthor("George R. R. Martin", "George Raymond Richard Martin is an American novelist and short-story writer in the fantasy, horror, and science fiction genres, screenwriter, and television producer. He is best known for his series of epic fantasy novels, A Song of Ice and Fire, which was later adapted into the HBO series Game of Thrones. Martin has won numerous awards for his work, including five Hugo Awards, two Nebula Awards, and a Bram Stoker Award. He was inducted into the Science Fiction and Fantasy Hall of Fame in 2014.", 3L);
        createAuthor("J. R. R. Tolkien", "John Ronald Reuel Tolkien, CBE was an English writer, poet, philologist, and university professor who is best known as the author of the classic high fantasy works The Hobbit, The Lord of the Rings, and The Silmarillion. He served as the Rawlinson and Bosworth Professor of Anglo-Saxon at the University of Oxford from 1925 to 1945 and Merton Professor of English Language and Literature at Oxford from 1945 to 1959. He was a close friend of C. S. Lewis—they were both members of the informal literary discussion group known as the Inklings. Tolkien was appointed a Commander of the Order of the British Empire by Queen Elizabeth II on 28 March 1972.", 4L);


        Address address = Address.builder().city("Mendoza").street("Colon").number(178).build();
        createUser("Juan", "Martinez", "JMartine@hotmail.com", "123123", "Jmartinez", address, 2L);


        address = Address.builder().city("Guaymallen").street("Bandera de Los Andes").number(626).build();
        createUser("Andres", "Hernandez", "AHernandez@hotmail.com", "15141312", "AHernandez", address, 3L);


        address = Address.builder().city("Lujan").street("Cabral").number(635).build();
        createUser("Carlos", "Quinta", "CQuinta@hotmail.com", "767574737271", "CQuinta", address, 4L);


        address = Address.builder().city("Maipu").street("Italia").number(490).build();
        createUser("Manuel", "Ibanez", "MIbanez@hotmail.com", "123124", "MHibanez", address, 5L);

        address = Address.builder().city("Maipu").street("Italia").number(490).build();
        createAdmin("admin", "admin", "admin@hotmail.com", "admin", "admin", address, 5L);


        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        createBook("Harry Potter and the Philosopher's Stone", "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends, he faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.", 2001, 20, "https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg", 5252F, genres, authors, 1L);

    }

    public Genre createGenre(String name, Long id) {
        Genre genre = Genre.builder().name(name).build();
        genre.setId(id);
        return saveGenreIfDoesntExists(genre);
    }

    public Author createAuthor(String fullName, String biography, Long id) {
        Author author = Author.builder().fullName(fullName).biography(biography).build();
        author.setId(id);
        return saveAuthorIfDoesntExists(author);
    }

    public void createAdmin(String firstName, String lastName, String email, String password, String username, Address address, Long id) {
        User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).username(username).roles("ADMIN").build();
        user.setAddress(address);
        user.setId(id);
        saveUserIfDoesntExists(user);
    }

    public void createUser(String firstName, String lastName, String email, String password, String username, Address address, Long id) {
        User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).username(username).roles("USER").build();
        user.setAddress(address);
        user.setId(id);
        saveUserIfDoesntExists(user);
    }

    public void createBook(String title, String synopsis, int publicationYear, int stock, String imageSrc, Float price, List<Genre> genres, List<Author> authors, Long id) {
        Book book = Book.builder().title(title).synopsis(synopsis).publicationYear(publicationYear).stock(stock).imageSrc(imageSrc).price(price).genres(genres).authors(authors).build();
        book.setId(id);
        saveBookIfDoesntExists(book);
    }

    public Genre saveGenreIfDoesntExists(Genre genre) {
        try {
            return genreService.findById(genre.getId());
        } catch (Exception e) {
            try {
                return genreService.save(genre);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public Author saveAuthorIfDoesntExists(Author author) {
        try {
            return authorService.findById(author.getId());
        } catch (Exception e) {
            try {
                return authorService.save(author);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public void saveUserIfDoesntExists(User user) {
        try {
            userService.findById(user.getId());
        } catch (Exception e) {
            try {
                userService.save(user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveBookIfDoesntExists(Book book) {
        try {
            bookService.findById(book.getId());
        } catch (Exception e) {
            try {
                bookService.save(book);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
