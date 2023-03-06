package Skypro.work;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Задача по ID не найдена");
    }
}
