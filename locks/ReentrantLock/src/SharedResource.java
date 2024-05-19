import java.util.Objects;
import java.util.concurrent.locks.Lock;

public class SharedResource {
    boolean isAvailable = false;

    public void addItem(Lock lock) {
        try {
            lock.lock();
            System.out.println("Lock acquired by Thread: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch(Exception e) {
            //handle exception
        } finally {
            System.out.println("Released lock aquired by Thread: " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
