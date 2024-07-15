import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskScheduler {
    private Map<String, Task> tasks;
    private Map<String, List<String>> dependencies;
    private Set<String> completeTasks;
    private Set<String> runningTasks;

    public TaskScheduler(Map<String, Task> tasks,
                         Map<String, List<String>> dependencies,
                         Set<String> completeTasks,
                         Set<String> runningTasks) {
        this.tasks = tasks;
        this.dependencies = dependencies;
        this.completeTasks = completeTasks;
        this.runningTasks = runningTasks;
    }

    public void start() {
        List<Thread> threads = new ArrayList<>();

        while(completeTasks.size() < tasks.size()) {
            List<String> runnableTasks = getRunnableTask();

            for(String taskName : runnableTasks) {
                Task task = tasks.get(taskName);
                Thread th = new Thread(() -> {
                    task.run();
                    markTaskAsCompleted(taskName);
                });

                th.start();
                runningTasks.add(taskName);
                threads.add(th);
            }

            for(Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            threads.clear();
        }
    }

    private synchronized List<String> getRunnableTask() {
        List<String> runnableTask = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : dependencies.entrySet()) {
            String currTask = entry.getKey();
            if(!completeTasks.contains(currTask) && !runningTasks.contains(currTask)) {
                boolean canRun = true;
                for(String dependency : entry.getValue()) {
                    if(!completeTasks.contains(dependency)) {
                        canRun = false;
                        break;
                    }
                }
                if(canRun) runnableTask.add(currTask);
            }
        }
        return runnableTask;
    }

    private synchronized void markTaskAsCompleted(String taskName) {
        completeTasks.add(taskName);
        runningTasks.remove(taskName);
    }
}
