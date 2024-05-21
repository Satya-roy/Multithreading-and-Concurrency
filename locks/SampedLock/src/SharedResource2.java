import java.util.concurrent.locks.StampedLock;

public class SharedResource2 {
    int a = 10;

    public void producer(StampedLock lock) {
        long stamp = lock.readLock();
        try{
            System.out.println("Taken optimistic lock");
            a = 11;
            Thread.sleep(6000);

            if(lock.validate(stamp)) {
                System.out.println("Update a value successfully");
            } else {
                System.out.println("rollback to work");
                a = 10;
            }
        }
        catch(Exception e) {
            //handle exception
        }
    }

    public void consumer(StampedLock lock) {
        long stamp = lock.writeLock();
        System.out.println("write lock acquired by : " + Thread.currentThread().getName());

        try {
            System.out.println("performing work");
            a = 9;
        }
        finally {
            System.out.println("Write lock released by : " + Thread.currentThread().getName());
            lock.unlockWrite(stamp);
        }
    }
}
