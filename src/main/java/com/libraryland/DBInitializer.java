package com.libraryland;

import com.libraryland.entities.Address;
import com.libraryland.entities.Author;
import com.libraryland.entities.Genre;
import com.libraryland.entities.User;
import com.libraryland.services.AuthorService;
import com.libraryland.services.GenreService;
import com.libraryland.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInitializer {
    private final GenreService genreService;
    private final AuthorService authorService;
    private final UserService userService;
    
    public DBInitializer(GenreService genreService, AuthorService authorService, UserService userService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.userService = userService;        
    }

    @PostConstruct
    private void initializeDB() {
        // Initialize tasks
        createGenre("Fiction", 1L);
        createGenre("Non-Fiction", 2L);
        createGenre("Sciencie Fiction", 3L);
        createGenre("Fantasy", 4L);

        createAuthor("J. K. Rowling", "Joanne Rowling, better known by her pen name J. K. Rowling, is a British author, philanthropist, film producer, television producer, and screenwriter. She is best known for writing the Harry Potter fantasy series, which has won multiple awards and sold more than 500 million copies, becoming the best-selling book series in history. The books are the basis of a popular film series, over which Rowling had overall approval on the scripts and was a producer on the final films. She also writes crime fiction under the pen name Robert Galbraith.", 1L);
        createAuthor("Stephen King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, and fantasy novels. His books have sold more than 350 million copies, and many have been adapted into films, television series, miniseries, and comic books. King has published 54 novels, including seven under the pen name Richard Bachman and six non-fiction books. He has written nearly 200 short stories, most of which have been collected in book collections. Many of his stories are set in his home state of Maine.", 2L);
        createAuthor("George R. R. Martin", "George Raymond Richard Martin is an American novelist and short-story writer in the fantasy, horror, and science fiction genres, screenwriter, and television producer. He is best known for his series of epic fantasy novels, A Song of Ice and Fire, which was later adapted into the HBO series Game of Thrones. Martin has won numerous awards for his work, including five Hugo Awards, two Nebula Awards, and a Bram Stoker Award. He was inducted into the Science Fiction and Fantasy Hall of Fame in 2014.", 3L);
        createAuthor("J. R. R. Tolkien", "John Ronald Reuel Tolkien, CBE was an English writer, poet, philologist, and university professor who is best known as the author of the classic high fantasy works The Hobbit, The Lord of the Rings, and The Silmarillion. He served as the Rawlinson and Bosworth Professor of Anglo-Saxon at the University of Oxford from 1925 to 1945 and Merton Professor of English Language and Literature at Oxford from 1945 to 1959. He was a close friend of C. S. Lewis—they were both members of the informal literary discussion group known as the Inklings. Tolkien was appointed a Commander of the Order of the British Empire by Queen Elizabeth II on 28 March 1972.", 4L);


        
        Address address = Address.builder().city("Mendoza").street("Colon").number(178).build();                
        createUser("Juan","Martinez","JMartine@hotmail.com","123123","Jmartinez",address,2L);        


        address = Address.builder().city("Guaymallen").street("Bandera de Los Andes").number(626).build();                
        createUser("Andres","Hernandez","AHernandez@hotmail.com","15141312","AHernandez",address,3L);


        address = Address.builder().city("Lujan").street("Cabral").number(635).build();                
        createUser("Carlos","Quinta","CQuinta@hotmail.com","767574737271","CQuinta",address,4L);


        address = Address.builder().city("Maipu").street("Italia").number(490).build();                
        createUser("Manuel","Ibanez","MIbanez@hotmail.com","123124","MHibanez",address,5L);
    }

    public void createGenre(String name, Long id) {
        Genre genre = Genre.builder().name(name).build();
        genre.setId(id);
        saveGenreIfDoesntExists(genre);
    }

    public void createAuthor(String fullName, String biography, Long id) {
        Author author = Author.builder().fullName(fullName).biography(biography).build();
        author.setId(id);
        saveAuthorIfDoesntExists(author);
    }

    public void createUser(String firstName, String lastName,String email,String password,String username,Address address ,Long id) {
        User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).username(username).build();
        user.setAddress(address);
        user.setId(id);
        saveUserIfDoesntExists(user);
    }
    public void saveGenreIfDoesntExists(Genre genre) {
        try {
            genreService.findById(genre.getId());
        } catch (Exception e) {
            try {
                genreService.save(genre);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveAuthorIfDoesntExists(Author author) {
        try {
            authorService.findById(author.getId());
        } catch (Exception e) {
            try {
                authorService.save(author);
            } catch (Exception ex) {
                ex.printStackTrace();
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
}
