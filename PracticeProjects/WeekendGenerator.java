package PracticeProjects;

import java.time.LocalDate;

public class WeekendGenerator {
    static final LocalDate start = LocalDate.of(2023, 8, 18);
    static final LocalDate end = LocalDate.of(2024, 8, 18);

    public static void main(String[] args) {
        LocalDate current = start;
        while (current.isBefore(end)) {
        }
    }
}
