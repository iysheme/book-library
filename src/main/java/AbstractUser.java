import java.util.ArrayList;

public class AbstractUser implements User {
    private String firstName;
    private String lastName;

    private ArrayList<Book> borrowedBooks;

    public AbstractUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

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
        return "FirstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
