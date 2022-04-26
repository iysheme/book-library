public class Student extends AbstractUser implements Comparable<User> {
    enum StudentType {
        JUNIOR,
        SENIOR,
    }

    private final StudentType studentType;

    public Student(String firstName, String lastName, StudentType studentType) {
        super(firstName, lastName);
        this.studentType = studentType;
    }

    @Override
    public int compareTo(User user) {
        if (user instanceof Student) {
            Student student = (Student) user;
            if (student.studentType == StudentType.SENIOR) {
                if (studentType == StudentType.JUNIOR) return -1;
            }
            else {
                if (studentType == StudentType.SENIOR) return 1;
            }
        }
        else {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                "studentType=" + studentType +
                '}';
    }
}
