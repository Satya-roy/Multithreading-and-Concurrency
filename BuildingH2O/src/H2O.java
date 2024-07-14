import java.util.concurrent.Semaphore;

public class H2O {
    Semaphore hydrogenSemaphore = new Semaphore(2);
    Semaphore oxygenSemaphore = new Semaphore(0);
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        releaseHydrogen.run();
        oxygenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire(2);
        releaseOxygen.run();
        hydrogenSemaphore.release(2);
    }
}
