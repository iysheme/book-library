import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final Library library = Library.getInstance();

    private static User[] users;

    public static void main(String[] args) {
        BasicConfigurator.configure();

        createAndAddBooks();

        generateUsers();

        library.addUsers(users);
        System.out.println();

        randomlyRequestForBooks();
        System.out.println();

        library.giveBooks();
        System.out.println();

        returnRandomBook();
        System.out.println();

        randomlyRequestForBooks();
        System.out.println();

        returnRandomBook();
        System.out.println();

        returnRandomBook();
        System.out.println();

        returnRandomBook();
        System.out.println();

        randomlyRequestForBooks();
        System.out.println();

        returnBooks();
        System.out.println();
    }

    private static void createAndAddBooks() {
        logger.trace("Creating books.......");

        for (int i = 1; i <= 10; i++) {
            Book book = new Book("title" + i, "author" + i);
            library.addBook(book);
        }

        logger.trace("Books created successfully!");
    }

    private static void generateUsers() {
        users = new User[10];
        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) users[i] = new Teacher("teacher" + i + 1, "teacher" + i + 1);
            else if (i % 3 == 0) users[i] = new Student("student" + i + 1, "student" + i + 1, Student.StudentType.SENIOR);
            else users[i] = new Student("student" + i + 1, "student" + i + 1, Student.StudentType.JUNIOR);
        }
    }

    private static void randomlyRequestForBooks() {
        Integer[] userIndices = new Integer[users.length];
        for (int i = 0; i < userIndices.length; i++) {
            userIndices[i] = i;
        }

        List<Integer> randomIndices = Arrays.asList(userIndices);
        Collections.shuffle(randomIndices);

        randomIndices.forEach(index -> {
            Book randomBook = library.getRandomBook();
            randomBook.addToWaitingList(users[index]);
            logger.debug("User requested for book");
        });
    }

    private static void returnRandomBook() {
        for (User user : users) {
            int size = user.getBorrowedBooks().size();
            if (size <= 0) continue;

            Book book = user.getBorrowedBooks().get(new Random().nextInt(user.getBorrowedBooks().size()));
            library.returnBook(book, user);
        }
    }

    private static void returnBooks() {
        for (User user : users) {
            library.returnBooks(user);
        }
    }
}
