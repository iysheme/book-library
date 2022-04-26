import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    public void testCompareTo() {
        Teacher teacher = new Teacher("name");
        Teacher teacher2 = new Teacher("name");
        Student student = new Student("name", Student.Grade.JUNIOR);

        assertEquals(teacher.compareTo(teacher2), 0);
        assertEquals(teacher.compareTo(student), 1);
    }

}