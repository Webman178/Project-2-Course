package Skypro.work;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private static int idGenerator = 0;
    private String title;
    private Type type;
    private int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        this.id = idGenerator++;
        this.type = type;
        this.dateTime = dateTime;
        setDescription(description);
        setTitle(title);

    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title.isBlank()) {
            throw new IncorrectArgumentException("Не заполнен заголовок");
        }
        this.title = title;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description.isBlank()) {
            throw new IncorrectArgumentException("Не заполнено описание");
        }
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    abstract public boolean appearsIn(LocalDate date);

    @Override
    public String toString() {
        return "№ - " + id + " Название - " + title + " тип - " + type + " Дата - " +
                dateTime + " описание - " + description;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + title.hashCode() + dateTime.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null && obj.getClass() != this.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return task.description.equals(this.description) && task.title.equals(this.title) &&
                task.dateTime.equals(this.dateTime) && task.type == this.type;
    }

} //task
