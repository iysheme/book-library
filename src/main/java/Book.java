import java.util.PriorityQueue;

public class Book {
    enum BookStatus {
        AVAILABLE,
        BORROWED,
    }

    private String title;
    private String author;

    private BookStatus status;
    private PriorityQueue<User> waitingList;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;

        status = BookStatus.AVAILABLE;
        waitingList = new PriorityQueue<>();
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void addToWaitingList(User user) {
        waitingList.add(user);
    }

    public User getNextWaitingListUser() {
        return waitingList.remove();
    }

    public boolean hasWaitingUsers() {
        return waitingList.size() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                '}';
    }
}
