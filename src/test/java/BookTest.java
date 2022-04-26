import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void testSetStatus() {
        Book book = new Book("Test Title", "Test Author");
        book.setStatus(Book.BookStatus.BORROWED);

        assertEquals(book.getStatus(), Book.BookStatus.BORROWED);
    }

    @Test
    public void testGetStatus() {
        Book book = new Book("Test Title", "Test Author");

        assertEquals(book.getStatus(), Book.BookStatus.AVAILABLE);
    }

    @Test
    public void testAddToWaitingList() {
        Book book = new Book("Test Title", "Test Author");
        User user = new Student("f", "l", Student.StudentType.JUNIOR);
        book.addToWaitingList(user);
        assertTrue(book.hasWaitingUsers());
    }

    @Test
    public void testGetNextWaitingListUser() {
        Book book = new Book("Test Title", "Test Author");
        User user = new Student("f", "l", Student.StudentType.JUNIOR);
        book.addToWaitingList(user);
        assertEquals(book.getNextWaitingListUser(), user);
    }

    @Test
    public void testHasWaitingUsers() {
        Book book = new Book("Test Title", "Test Author");
        assertFalse(book.hasWaitingUsers());
    }

}