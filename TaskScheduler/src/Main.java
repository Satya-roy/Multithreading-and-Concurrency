import java.util.*;
import java.util.concurrent.FutureTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Task1");
        Task task2 = new Task("Task2");
        Task task3 = new Task("Task3");
        Task task4 = new Task("Task4");

        Map<String, Task> tasks = new HashMap<>();
        tasks.put("Task1", task1);
        tasks.put("Task2", task2);
        tasks.put("Task3", task3);
        tasks.put("Task4", task4);

        Map<String, List<String>> dependencies = new HashMap<>();
        dependencies.put("Task1", new ArrayList<>());
        dependencies.put("Task2", new ArrayList<>());
        dependencies.put("Task3", List.of("Task1", "Task2"));
        dependencies.put("Task4", List.of("Task2"));

        TaskScheduler scheduler = new TaskScheduler(tasks, dependencies, new HashSet<>(), new HashSet<>());
        scheduler.start();
    }
}