import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public final class Library {
    private static final Library INSTANCE = new Library();

    private final HashMap<Book, Integer> books;
    private final ArrayList<User> users;

    private static final Logger logger = Logger.getLogger(Library.class.getName());

    private Library() {
        books = new HashMap<>();
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

    public void addUser(User user) {
        if (users.contains(user)) {
            logger.warn(LogMessages.USER_EXISTS);
        }
        else {
            users.add(user);
            logger.info(LogMessages.NEW_USER_ADDED);
        }
    }

    public void addUsers(User[] users) {
        for (User user : users) {
            addUser(user);
        }
        logger.info(LogMessages.ADD_NEW_USERS);
    }

    public void addBook(Book book) {
        if (books.containsKey(book)) {
            books.put(book, books.get(book) + 1);
            logger.warn(LogMessages.EXISTING_BOOK_ADDED);
        }
        else {
            books.put(book, 1);
            logger.info(LogMessages.NEW_BOOK_ADDED);
        }
    }

    public void addBooks(Book[] books) {
        for (Book book : books) {
            addBook(book);
        }
        logger.info(LogMessages.ADD_NEW_BOOKS);
    }

    public boolean invalidate(Book book, User user) {
        if (!users.contains(user)) {
            logger.warn(LogMessages.USER_NOT_FOUND);
            return true;
        }

        if (!books.containsKey(book)) {
            logger.warn(LogMessages.BOOK_NOT_FOUND);
            return true;
        }

        return false;
    }

    public void requestBook(Book book, User user) {
        if (invalidate(book, user)) return;

        book.addToWaitingList(user);
        logger.info(LogMessages.BOOK_REQUESTED);
    }

    public void giveBook(Book book, User user) {
        if (invalidate(book, user)) return;

        if (book.getStatus() == Book.Status.AVAILABLE) {
            if (book.hasWaitingUsers()) {
                User nextWaitingUser = book.getNextWaitingListUser();
                if (nextWaitingUser == user) {
                    nextWaitingUser.borrowBook(book);
                    book.setStatus(Book.Status.BORROWED);
                    books.put(book, books.get(book) - 1);

                    logger.info(LogMessages.BORROW_BOOK);
                }
                else {
                    logger.warn(LogMessages.BOOK_NOT_REQUESTED);
                }
            }
            else {
                logger.warn(LogMessages.BOOK_NOT_REQUESTED);
            }
        }
        else {
            logger.warn(LogMessages.BOOK_UNAVAILABLE);
        }
    }

    public void returnBook(Book book, User user) {
        if (invalidate(book, user)) return;

        user.getBorrowedBooks().remove(book);
        book.setStatus(Book.Status.AVAILABLE);
        books.put(book, books.get(book) + 1);

        logger.info(LogMessages.RETURN_ONE_BOOK);
    }

    public void returnBooks(User user) {
        ArrayList<Book> borrowedBooks = user.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            book.setStatus(Book.Status.AVAILABLE);
        }
        borrowedBooks.clear();

        logger.info(LogMessages.RETURN_ALL_BOOKS);
    }

    public void printBooks() {
        books.forEach( (book, occurrence) -> System.out.println(book + " has " + occurrence + " copies"));
    }
}
