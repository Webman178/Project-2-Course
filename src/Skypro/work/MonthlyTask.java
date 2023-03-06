package Skypro.work;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.getDayOfMonth() == getDateTime().toLocalDate().getDayOfMonth();
    }

    @Override
    public String toString() {
        return super.toString() + " DayOfMonth ";
    }
}
