import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<Integer> buffer;
    private final int maxSize;
    public SharedResource() {
        this.buffer = new LinkedList<>();
        this.maxSize = 10;
    }

    public synchronized void addItem(int val) {
        while (buffer.size() == maxSize) {
            try {
                System.out.println("Buffer is full, Thread: " + Thread.currentThread().getName() + " please wait.");
                wait();
            } catch(Exception e) {
                //handle exception
            }
        }
        buffer.add(val);
        System.out.println("Produce item : " + val);
        notify(); //as the value is added to the buffer notify the threads that are in waiting state for the same object
    }

    public synchronized void consumeItem() {
        while(buffer.isEmpty()) {
            try {

                System.out.println("Buffer has no element to consume Thread: " + Thread.currentThread().getName() + " please wait.");
                wait();
            } catch(Exception e) {
                //handle Exception
            }
        }
        int val = buffer.poll();
        System.out.println("Thread: " + Thread.currentThread().getName() + " has consume " + val);
        notify();
    }
}
