import java.util.concurrent.locks.StampedLock;

public class SharedResource1 {
    boolean isAvailable = false;

    public void producer(StampedLock lock) {

        long stamp = lock.readLock(); //work as a version
        try {
            System.out.println("Read lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(6000);
        } catch (Exception e) {
            //handle exception
        } finally {
            lock.unlock(stamp);
            System.out.println("Read lock release by: " + Thread.currentThread().getName());
        }
    }

    public void consumer(StampedLock lock) {
        long stamp = lock.writeLock();
        try{
            System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(6000);
        } catch (Exception e){
            //handle exception
        }
        finally {
            lock.unlockWrite(stamp);
            System.out.println("Write lock released by: " +  Thread.currentThread().getName());
        }
    }
}
