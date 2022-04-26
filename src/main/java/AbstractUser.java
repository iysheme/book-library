import java.util.ArrayList;

public class AbstractUser implements User {
    private String name;

    private final ArrayList<Book> borrowedBooks;

    public AbstractUser(String name) {
        this.name = name;

        borrowedBooks = new ArrayList<>();
    }

    @Override
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "Name = '" + name + '\'';
    }
}
