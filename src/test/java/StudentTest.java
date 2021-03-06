import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    public void testCompareTo() {
        Student student1 = new Student("name", Student.Grade.JUNIOR);
        Student student2 = new Student("name", Student.Grade.SENIOR);

        Teacher teacher = new Teacher("name");

        assertEquals(student1.compareTo(student2), -1);
        assertEquals(student2.compareTo(student1), 1);

        assertEquals(student1.compareTo(student1), 0);
        assertEquals(student2.compareTo(student2), 0);

        assertEquals(student1.compareTo(teacher), -1);
        assertEquals(student2.compareTo(teacher), -1);
    }

}