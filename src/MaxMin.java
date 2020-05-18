import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MaxMin {
    public static Student select(List<Student> students, Comparator<Student> comparator) {
        return students.stream().max(comparator).orElse(null);
    }

    public static void main(String[] args) {
        Random r = new Random();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student.Builder("학생" + i)
                    .setScore("c", r.nextInt(101))
                    .setScore("java", r.nextInt(101))
                    .setScore("python", r.nextInt(101))
                    .build();
            students.add(student);
        }
    }
}
