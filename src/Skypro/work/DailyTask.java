package Skypro.work;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends  Task{
    public DailyTask(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " DailyTask ";
    }
}
