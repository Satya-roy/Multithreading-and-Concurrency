// Step1. Create a class 
public class MyClass implements Runnable {
    @Override
    public void run() {
        System.out.println("Code executed by thread: " + Thread.currentThread().getName());
    }
}
