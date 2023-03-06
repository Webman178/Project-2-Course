package Skypro.work;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.getMonth() == getDateTime().toLocalDate().getMonth() &&
                date.getDayOfMonth() == getDateTime().toLocalDate().getDayOfMonth();
    }

    @Override
    public String toString() {
        return super.toString() + " YearlyTask ";
    }
}
