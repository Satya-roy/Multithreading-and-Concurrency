import java.util.concurrent.locks.ReentrantLock;

// Though th1 and th2 are using different objects of shared resources.
// Still critical section is acquired by threads using reentrantLock
public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started");

        ReentrantLock lock = new ReentrantLock();

        SharedResource sharedResource1 = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();
        SharedResource sharedResource3 = new SharedResource();

        Thread th1 = new Thread(() -> {
            sharedResource1.addItem(lock);
        });

        Thread th2 = new Thread(() -> {
            sharedResource2.addItem(lock);
        });

        Thread th3 = new Thread(new ProducerTask(sharedResource3, lock));

        th1.start();
        th2.start();
        th3.start();

        System.out.println("Main thread is finished");
    }
}