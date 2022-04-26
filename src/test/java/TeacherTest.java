import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    public void testCompareTo() {
        Teacher teacher = new Teacher("f", "l");
        Teacher teacher2 = new Teacher("f2", "l2");
        Student student = new Student("f", "l", Student.StudentType.JUNIOR);

        assertEquals(teacher.compareTo(teacher2), 0);
        assertEquals(teacher.compareTo(student), 1);
    }

}