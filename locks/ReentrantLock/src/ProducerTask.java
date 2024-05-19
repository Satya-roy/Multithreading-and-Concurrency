import java.util.concurrent.locks.ReentrantLock;

public class ProducerTask implements Runnable{

    private SharedResource sharedResource;
    private ReentrantLock lock;
    public ProducerTask(SharedResource sharedResource, ReentrantLock lock) {
        this.sharedResource = sharedResource;
        this.lock = lock;
    }
    @Override
    public void run() {
        sharedResource.addItem(lock);
    }
}
