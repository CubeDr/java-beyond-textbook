import java.util.HashMap;
import java.util.Map;

public class Student {
    public final String name;
    private final Map<String, Integer> scores;

    private Student(Builder builder) {
        this.name = builder.name;
        this.scores = new HashMap<>(builder.scores);
    }

    static class Builder {
        private final String name;
        private final Map<String, Integer> scores;

        public Builder(String name) {
            this.name = name;
            this.scores = new HashMap<>();
        }

        public Builder setScore(String subject, int score) {
            scores.put(subject, score);
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public int getScore(String subject) throws NoSubjectException {
        try {
            return scores.get(subject);
        } catch (Exception ignored) {
            throw new NoSubjectException(subject);
        }
    }

    public static class NoSubjectException extends Exception {
        public final String subject;

        public NoSubjectException(String subject) {
            this.subject = subject;
        }
    }
}
