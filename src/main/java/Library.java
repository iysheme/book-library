import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class Library {
    private static final Library INSTANCE = new Library();

    private final ArrayList<Book> books;
    private final ArrayList<User> users;

    private static final Logger logger = Logger.getLogger(Library.class.getName());

    private Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();

        BasicConfigurator.configure();
    }

    public static Library getInstance() {
        return INSTANCE;
    }

    public boolean hasBooks() {
        return books.size() != 0;
    }

    public boolean hasUsers() {
        return users.size() != 0;
    }

    public void addBook(Book book) {
        books.add(book);
        logger.debug("New book added to library");
    }

    public Book getRandomBook() {
        return books.get(new Random().nextInt(books.size()));
    }

    public void returnBook(Book book, User user) {
        user.getBorrowedBooks().remove(book);
        book.setStatus(Book.BookStatus.AVAILABLE);
        logger.debug("Book returned to library");
    }

    public void returnBooks(User user) {
        ArrayList<Book> borrowedBooks = user.getBorrowedBooks();
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);
            user.getBorrowedBooks().remove(book);
            book.setStatus(Book.BookStatus.AVAILABLE);
        }
        logger.debug("User returned all borrowed books to library");
    }

    public void giveBook(Book book, User user) {
        if (book.getStatus() == Book.BookStatus.AVAILABLE && book.hasWaitingUsers()) {
            User nextWaitingUser = book.getNextWaitingListUser();
            if (nextWaitingUser == user) {
                nextWaitingUser.borrowBook(book);
                book.setStatus(Book.BookStatus.BORROWED);
                logger.debug("User collected a book from library");
            }
        }
    }

    public void giveBooks() {
        books.forEach(book -> {
            if (book.getStatus() == Book.BookStatus.AVAILABLE && book.hasWaitingUsers()) {
                User nextWaitingUser = book.getNextWaitingListUser();
                nextWaitingUser.borrowBook(book);
                book.setStatus(Book.BookStatus.BORROWED);
                System.out.printf("%s collected %s\n", nextWaitingUser, book);
            }
        });
    }

    public void addUsers(User[] users) {
        this.users.addAll(Arrays.asList(users));
        for (User user : users) {
            logger.debug("New user added to library");
        }
    }

    public void printBooks() {
        books.forEach(System.out::println);
    }
}
