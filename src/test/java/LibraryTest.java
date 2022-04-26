import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testAddBook() {
        Library library = Library.getInstance();
        Book book = new Book("Test Title", "Test Author");
        library.addBook(book);

        assertTrue(library.hasBooks());
    }

    @Test
    public void testGetRandomBook() {
        Library library = Library.getInstance();
        Book book = new Book("Test Title", "Test Author");
        library.addBook(book);
        Book book1 = library.getRandomBook();
        assertTrue(book.equals(book1));
    }

    @Test
    public void testReturnBook() {
        Library library = Library.getInstance();
        Book book = new Book("Test Title", "Test Author");
        User teacher = new Teacher("f", "l");

        library.addBook(book);
        book.addToWaitingList(teacher);
        library.giveBook(book, teacher);
        library.returnBook(book, teacher);

        assertTrue(library.hasBooks());
    }

    @Test
    public void testReturnBooks() {
        Library library = Library.getInstance();
        Book book1 = new Book("Test Title", "Test Author");
        Book book2 = new Book("Test Title", "Test Author");
        Book book3 = new Book("Test Title", "Test Author");

        User teacher = new Teacher("f", "l");

        library.addBook(book1); library.addBook(book2); library.addBook(book3);
        book1.addToWaitingList(teacher); book2.addToWaitingList(teacher); book3.addToWaitingList(teacher);
        library.giveBook(book1, teacher); library.giveBook(book2, teacher); library.giveBook(book3, teacher);
        library.returnBooks(teacher);

        assertTrue(library.hasBooks());
    }

    @Test
    void addUsers() {
        Library library = Library.getInstance();
        User[] users = new User[]{
                new Teacher("f", "l"),
                new Student("f", "l", Student.StudentType.SENIOR),
                new Student("f", "l", Student.StudentType.JUNIOR),
        };
        library.addUsers(users);

        assertTrue(library.hasUsers());
    }
}