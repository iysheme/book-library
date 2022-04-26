public class Student extends AbstractUser implements Comparable<User> {
    enum Grade {
        JUNIOR,
        SENIOR,
    }

    private final Grade grade;

    public Student(String name, Grade grade) {
        super(name);
        this.grade = grade;
    }

    @Override
    public int compareTo(User user) {
        if (user instanceof Student) {
            Student student = (Student) user;
            if (student.grade == Grade.SENIOR) {
                if (grade == Grade.JUNIOR) return -1;
            }
            else {
                if (grade == Grade.SENIOR) return 1;
            }
        }
        else {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Student(" +
                super.toString() +
                "Grade = " + grade +
                ')';
    }
}
