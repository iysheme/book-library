import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.PriorityQueue;

public class Book {
    enum Status {
        AVAILABLE,
        BORROWED,
    }

    private final String title;
    private final String author;
    private final int editionYear;

    private Status status;
    private final PriorityQueue<User> waitingList;

    private static final Logger logger = Logger.getLogger(Book.class.getName());

    public Book(String title, String author, int editionYear) {
        this.title = title;
        this.author = author;
        this.editionYear = editionYear;

        status = Status.AVAILABLE;
        waitingList = new PriorityQueue<>();

        BasicConfigurator.configure();
    }

    public void setStatus(Status status) {
        this.status = status;
        logger.info(LogMessages.BOOK_STATUS_SET);
    }

    public Status getStatus() {
        return status;
    }

    public boolean hasWaitingUsers() {
        return !waitingList.isEmpty();
    }

    public void addToWaitingList(User user) {
        if (waitingList.contains(user)) return;

        waitingList.add(user);
        logger.info(LogMessages.AWAITING_BOOK);
    }

    public User getNextWaitingListUser() {
        return waitingList.remove();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title.equals(book.title) && author.equals(book.author) && editionYear == book.editionYear;
    }

    @Override
    public String toString() {
        return "Book(" +
                "Title = '" + title + '\'' +
                ", Author = '" + author + '\'' +
                ", Edition Year = '" + editionYear +
                ')';
    }
}
