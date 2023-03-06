package Skypro.work;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.getDayOfWeek() == getDateTime().toLocalDate().getDayOfWeek();
    }

    @Override
    public String toString() {
        return super.toString() + " WeeklyTask ";
                }
                }
