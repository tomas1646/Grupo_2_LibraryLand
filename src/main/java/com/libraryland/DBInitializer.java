package com.libraryland;

import com.libraryland.entities.*;
import com.libraryland.services.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DBInitializer {
    private final GenreService genreService;
    private final AuthorService authorService;
    private final UserService userService;
    private final BookService bookService;

    private final OrderService orderService;

    public DBInitializer(GenreService genreService, AuthorService authorService, UserService userService, BookService bookService, OrderService orderService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @PostConstruct
    private void initializeDB() throws Exception {
        // Initialize tasks
        Genre genre1 = createGenre("Fiction", 1L);
        Genre genre2 = createGenre("Non-Fiction", 2L);
        Genre genre3 = createGenre("Sciencie Fiction", 3L);
        Genre genre4 = createGenre("Fantasy", 4L);
        Genre genre5 = createGenre("Novel", 5L);
        Genre genre6 = createGenre("Philosophical novel", 6L);

        Author author1 = createAuthor("J. K. Rowling", "Joanne Rowling, better known by her pen name J. K. Rowling, is a British author, philanthropist, film producer, television producer, and screenwriter. She is best known for writing the Harry Potter fantasy series, which has won multiple awards and sold more than 500 million copies, becoming the best-selling book series in history. The books are the basis of a popular film series, over which Rowling had overall approval on the scripts and was a producer on the final films. She also writes crime fiction under the pen name Robert Galbraith.", 1L);
        Author author2 = createAuthor("Stephen King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, and fantasy novels. His books have sold more than 350 million copies, and many have been adapted into films, television series, miniseries, and comic books. King has published 54 novels, including seven under the pen name Richard Bachman and six non-fiction books. He has written nearly 200 short stories, most of which have been collected in book collections. Many of his stories are set in his home state of Maine.", 2L);
        Author author3 = createAuthor("George R. R. Martin", "George Raymond Richard Martin is an American novelist and short-story writer in the fantasy, horror, and science fiction genres, screenwriter, and television producer. He is best known for his series of epic fantasy novels, A Song of Ice and Fire, which was later adapted into the HBO series Game of Thrones. Martin has won numerous awards for his work, including five Hugo Awards, two Nebula Awards, and a Bram Stoker Award. He was inducted into the Science Fiction and Fantasy Hall of Fame in 2014.", 3L);
        Author author4 = createAuthor("J. R. R. Tolkien", "John Ronald Reuel Tolkien, CBE was an English writer, poet, philologist, and university professor who is best known as the author of the classic high fantasy works The Hobbit, The Lord of the Rings, and The Silmarillion. He served as the Rawlinson and Bosworth Professor of Anglo-Saxon at the University of Oxford from 1925 to 1945 and Merton Professor of English Language and Literature at Oxford from 1945 to 1959. He was a close friend of C. S. Lewis—they were both members of the informal literary discussion group known as the Inklings. Tolkien was appointed a Commander of the Order of the British Empire by Queen Elizabeth II on 28 March 1972.", 4L);
        Author author5 = createAuthor("Julio Cortazar", "Julio Cortázar, pseudonym Julio Denis, (born August 26, 1914, Brussels, Belgium—died February 12, 1984, Paris, France), Argentine novelist and short-story writer who combined existential questioning with experimental writing techniques in his works.", 5L);
        Author author6 = createAuthor("Albert Camus", "Albert Camus, (born November 7, 1913, Mondovi, Algeria—died January 4, 1960, near Sens, France), French novelist, essayist, and playwright, best known for such novels as L’Étranger (1942; The Stranger), La Peste (1947; The Plague), and La Chute (1956; The Fall) and for his work in leftist causes. He received the 1957 Nobel Prize for Literature.", 6L);


        Address address = Address.builder().city("Mendoza").street("Colon").number(178).build();
        createUser("Juan", "Martinez", "JMartine@hotmail.com", "123123", "Jmartinez", address, 2L);


        address = Address.builder().city("Guaymallen").street("Bandera de Los Andes").number(626).build();
        createUser("Andres", "Hernandez", "AHernandez@hotmail.com", "15141312", "AHernandez", address, 3L);


        address = Address.builder().city("Lujan").street("Cabral").number(635).build();
        createUser("Carlos", "Quinta", "CQuinta@hotmail.com", "767574737271", "CQuinta", address, 4L);


        address = Address.builder().city("Maipu").street("Italia").number(490).build();
        createUser("Manuel", "Ibanez", "MIbanez@hotmail.com", "123124", "MHibanez", address, 5L);

        address = Address.builder().city("Maipu").street("Italia").number(490).build();
        User admin = createUser("admin", "admin", "admin@hotmail.com", "admin", "admin", address, 5L);
        admin.setRoles("ADMIN");
        userService.update(admin.getId(), admin);

        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        Book book1 = createBook("Harry Potter and the Philosopher's Stone", "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends, he faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.", 2001, 20, "e726f16b-87fb-4189-92f8-b327e9b93543.jpg", 5252F, genres, authors, 1L);

        genres.clear();
        authors.clear();
        genres.add(genre5);
        authors.add(author5);
        Book book2 = createBook("Rayuela", "Hopscotch is an account of the misadventures of Horacio Oliveira, a discontent Argentinean intellectual in his forties. He wanders 1950s Paris with his mistress La Maga and a band of bohemian cohorts, but a series of missteps and personal tragedies send him packing back to Buenos Aires.", 1963, 10, "aeac0605-274c-4e95-b8d6-afc6d74fedfe.png", 3000F, genres, authors, 2L);

        genres.clear();
        authors.clear();
        genres.add(genre5);
        genres.add(genre6);
        authors.add(author6);
        Book book3 = createBook("The Stranger", "Meursault, the narrator, is a young man living in Algiers. After receiving a telegram informing him of his mother’s death, he takes a bus to Marengo, where his mother had been living in an old persons’ home. He sleeps for almost the entire trip. When he arrives, he speaks to the director of the home. The director allows Meursault to see his mother, but Meursault finds that her body has already been sealed in the coffin. He declines the caretaker’s offer to open the coffin.", 1942, 5, "8e72b708-9536-4d39-8b8c-492b760d5eea._UY2400_SS2400_.jpg", 2000F, genres, authors, 3L);

        genres.clear();
        authors.clear();
        genres.add(genre4);
        authors.add(author5);
        Book book4 = createBook("All Fires The Fire", "In the story, a sick teenager navigates a crush on his young nurse (Cora). Their lives overlap as the patient's most private rituals, his bodily functions, become Nurse Cora's daily tasks and she becomes obsessed with his recovery.", 1966, 11, "918a7bc5-81f5-4e6b-9ab3-3f8afe5089a4.jpg", 1999.99F, genres, authors, 4L);

        genres.clear();
        authors.clear();
        genres.add(genre4);
        authors.add(author5);
        Book book5 = createBook("The Secret Weapons", "This present collection entails five stories with different plots and characters, all emphasizing the new level of imaginative reality that combines the internal feelings of people with the real happenings in reality. ", 1959, 0, "31344616-991f-48a4-90c0-8a1af923d8f6.jpg", 1382F, genres, authors, 5L);

        genres.clear();
        authors.clear();
        genres.add(genre4);
        authors.add(author5);
        createBook("We Love Glenda So Much", "It's about a group of film enthusiasts who become obsessed with an actress named Glenda Garson, who is clearly not too far away from the real-life Glenda. They so worship her performances that they become frustrated by the occasional imperfections in the fallible films that contain her.", 1980, 100, "2ec0a4ac-07d3-4768-bee4-a956d1019816._AC_UL600_SR600,600_.jpg", 1963F, genres, authors, 6L);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        createOrder(1L, admin, books);

        books.add(book2);
        books.add(book3);
        createOrder(2L, admin, books);

        books.add(book4);
        books.add(book5);
        createOrder(3L, admin, books);
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

    public User createUser(String firstName, String lastName, String email, String password, String username, Address address, Long id) {
        User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).username(username).build();
        user.setAddress(address);
        user.setId(id);
        return saveUserIfDoesntExists(user);
    }

    public Book createBook(String title, String synopsis, int publicationYear, int stock, String imageSrc, Float price, List<Genre> genres, List<Author> authors, Long id) {
        Book book = Book.builder().title(title).synopsis(synopsis).publicationYear(publicationYear).stock(stock).imageSrc(imageSrc).price(price).genres(genres).authors(authors).build();
        book.setId(id);
        return saveBookIfDoesntExists(book);
    }

    public void createOrder(Long id, User user, List<Book> books) {
        try {
            Order order = orderService.findById(id);
            return;
        } catch (Exception ignored) {

        }

        Random random = new Random();

        List<OrderDetail> details = new ArrayList<>();
        for (Book book : books) {
            details.add(OrderDetail.builder().book(book).quantity(random.nextInt(3) + 1).price(book.getPrice()).build());
        }

        double total = details.stream().mapToDouble(detail -> detail.getPrice() * detail.getQuantity()).sum();

        Order order = Order.builder().date(new Date()).number(random.nextInt() & Integer.MAX_VALUE).total(total).user(user).details(details).build();
        order.setId(id);
        try {
            orderService.save(order);
        } catch (Exception ignored) {

        }
    }

    public Genre saveGenreIfDoesntExists(Genre genre) {
        try {
            return genreService.findByName(genre.getName());
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
            return authorService.findByFullName(author.getFullName());
        } catch (Exception e) {
            try {
                return authorService.save(author);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public User saveUserIfDoesntExists(User user) {
        try {
            return userService.findById(user.getId());
        } catch (Exception e) {
            try {
                return userService.save(user);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public Book saveBookIfDoesntExists(Book book) {
        try {
            return bookService.findById(book.getId());
        } catch (Exception e) {
            try {
                return bookService.save(book);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}

