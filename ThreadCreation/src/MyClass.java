// Step1. Create a class that implements 'Runnable interface'.
// Step2. Implement the 'run()' method to tell the task what thread has to do.
public class MyClass implements Runnable {
    @Override
    public void run() {
        System.out.println("Code executed by thread: " + Thread.currentThread().getName());
    }
}
