import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {
    private Semaphore s1;
    private Semaphore s2;
    private Queue<Integer> queue;

    public BoundedBlockingQueue(int capacity) {
        this.s1 = new Semaphore(capacity);
        this.s2 = new Semaphore(0);
    }

    public void enqueue(int element) throws InterruptedException {
        s1.acquire();
        queue.add(element);
        s2.release();
    }

    public int dequeue() throws InterruptedException {
        s2.acquire();
        int ans = queue.poll();
        s1.release();
        return ans;
    }
}
