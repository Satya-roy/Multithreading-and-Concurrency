import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource1 = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();
        SharedResource sharedResource3 = new SharedResource();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread th1 = new Thread(() -> {
            sharedResource1.produce(lock);
        });

        Thread th2 = new Thread(() -> {
            sharedResource2.produce(lock);
        });

        Thread th3 = new Thread(() -> {
            sharedResource3.consume(lock);
        });

        th1.start();
        th2.start();
        th3.start();
    }
}