import java.util.concurrent.locks.StampedLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SharedResource1 sharedResource1 = new SharedResource1();
        StampedLock lock = new StampedLock();
        Thread th1 = new Thread(()-> {
            sharedResource1.producer(lock);
        });

        Thread th2 = new Thread(() -> {
            sharedResource1.producer(lock);
        });

        Thread th3 = new Thread(() -> {
            sharedResource1.consumer(lock);
        });
        
        th3.start();
        th1.start();
        th2.start();

    }
}