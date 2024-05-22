import java.util.concurrent.atomic.AtomicInteger;

public class ShareResourceThreadSafe {
    AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.addAndGet(1);
    }

    public int get() {
        return counter.get();
    }
}
