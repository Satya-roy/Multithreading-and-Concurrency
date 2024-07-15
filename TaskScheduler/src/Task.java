public class Task implements Runnable {
    private final String name;

    public Task(String _name) {
        this.name = _name;
    }

    @Override
    public void run() {
        System.out.println("Execute task: " + name);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Completed task: " + name);
    }
}