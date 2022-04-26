public class Teacher extends AbstractUser implements Comparable<User> {

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int compareTo(User user) {
        if (user instanceof Student) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                '}';
    }
}
