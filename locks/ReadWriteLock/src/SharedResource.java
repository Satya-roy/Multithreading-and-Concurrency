import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    public void produce(ReadWriteLock lock) {
        try {
            lock.readLock().lock();;
            System.out.println("Read lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (Exception e) {
            //handle exception
        } finally {
            System.out.println("Read lock released by: " + Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
        } catch(Exception e) {
            //handle exception
        } finally {
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }

}
