import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private static final Logger logger = Logger.getLogger(LibraryTest.class.getName());

    private Library library;

    @BeforeAll
    public static void setupAll() {
        //BasicConfigurator.configure();

        System.out.println("Beginning test methods for Library class...");
    }

    @BeforeEach
    public void setupEach() {
        library = Library.getInstance();
    }

    @Test
    public void testAddUser() {
        User user = new Teacher("name");
        library.addUser(user);
        library.addUser(user);

        assertTrue(library.hasUsers());
    }

    @Test
    void testAddUsers() {
        User[] users = new User[]{
                new Teacher("name"),
                new Student("name", Student.Grade.SENIOR),
                new Student("name", Student.Grade.JUNIOR),
        };
        library.addUsers(users);

        assertTrue(library.hasUsers());
    }

    @Test
    public void testAddBook() {
        Book book = new Book("title", "author", 2000);
        library.addBook(book);
        library.addBook(book);

        assertTrue(library.hasBooks());
    }

    @Test
    void testAddBooks() {
        Book[] books = new Book[]{
                new Book("title", "author", 2000),
                new Book("title", "author", 2000),
                new Book("title", "author", 2000),
        };
        library.addBooks(books);

        assertTrue(library.hasBooks());
    }

    @Test
    public void testRequestBook() {
        Book book = new Book("title", "author", 2000);
        User user = new Teacher("name");

        library.addUser(user);
        library.addBook(book);

        library.requestBook(book, user);

        assertTrue(book.hasWaitingUsers());
    }

    @Test
    public void testGiveBook() {
        Book book = new Book("title", "author", 2000);
        User user = new Teacher("name");

        library.addBook(book);
        library.addUser(user);

        library.requestBook(book, user);
        library.giveBook(book, user);

        assertEquals(book.getStatus(), Book.Status.BORROWED);
        assertTrue(user.getBorrowedBooks().contains(book));
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("title", "author", 2000);
        User user = new Teacher("name");

        library.addBook(book);
        library.addUser(user);

        library.returnBook(book, user);

        assertEquals(book.getStatus(), Book.Status.AVAILABLE);
        assertFalse(user.getBorrowedBooks().contains(book));
    }

    @Test
    public void testReturnBooks() {
        Book[] books = new Book[]{
                new Book("title", "author", 2000),
                new Book("title", "author", 2000),
                new Book("title", "author", 2000),
        };
        User user = new Teacher("name");

        library.addBooks(books);
        library.addUser(user);

        for (Book book : books) {
            library.requestBook(book, user);
            library.giveBook(book, user);
        }

        library.returnBooks(user);

        assertTrue(user.getBorrowedBooks().isEmpty());
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Finished test methods for Library class!");
    }
}