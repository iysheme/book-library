import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Beginning test methods for Book class...");
    }

    @BeforeEach
    public void setupEach() {
        book = new Book("title", "author", 2000);
    }

    @Test
    public void testGetStatus() {
        assertEquals(book.getStatus(), Book.Status.AVAILABLE);
    }

    @Test
    public void testSetStatus() {
        book.setStatus(Book.Status.BORROWED);

        assertEquals(book.getStatus(), Book.Status.BORROWED);
    }

    @Test
    public void testHasWaitingUsers() {
        assertFalse(book.hasWaitingUsers());
    }

    @Test
    public void testAddToWaitingList() {
        User user = new Student("name", Student.Grade.JUNIOR);
        book.addToWaitingList(user);
        assertTrue(book.hasWaitingUsers());
    }

    @Test
    public void testGetNextWaitingListUser() {
        User user1 = new Student("name", Student.Grade.JUNIOR);
        book.addToWaitingList(user1);

        assertEquals(book.getNextWaitingListUser(), user1);

        User user2 = new Student("name", Student.Grade.SENIOR);
        book.addToWaitingList(user2);

        assertEquals(book.getNextWaitingListUser(), user2);

        User user3 = new Teacher("name");
        book.addToWaitingList(user3);

        assertEquals(book.getNextWaitingListUser(), user3);
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Finished test methods for Book class!");
    }

}