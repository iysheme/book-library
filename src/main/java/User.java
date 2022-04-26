import java.util.ArrayList;

public interface User {
    void borrowBook(Book book);

    ArrayList<Book> getBorrowedBooks();
}
