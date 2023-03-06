package Skypro.work;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private Map<Integer, Task> taskMap;
    private Collection<Task> removedTask;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTask = new ArrayList<>();
    }

    public void add(Task a) {
        taskMap.put(a.getId(), a);
    }

    public Task remove(int id) throws TaskNotFoundException {
        Task b = taskMap.remove(id);
        if (b == null) {
            throw new TaskNotFoundException();
        }
        removedTask.add(b);
        return b;
    }

    public Collection<Task> getAllByDate(LocalDate c) {
        Collection<Task> d = taskMap.values().stream().filter(x -> x.appearsIn(c)).collect(Collectors.toList());
        return d;
    }

    public void printTaskByDate(LocalDate e) {
        Collection<Task> tasksByDay = getAllByDate(e);
        if (tasksByDay == null) {
            System.out.println("Задач на этот день нет " + e);
            return;
        }
        System.out.println("Задачи на " + e + " присутствуют");
        for (Task task : tasksByDay) {
            System.out.println(task);
        }
    }

    public void printAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("Список задач пустой");
            return;
        }
        System.out.println("Список задач");
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
