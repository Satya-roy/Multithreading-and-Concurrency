import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableExample {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10));

        Future<List<Integer>> futureObj = poolExecutor.submit(() -> {
            List<Integer> output = new ArrayList<>();
            output.add(100);
            output.add(200);
            output.add(300);
            System.out.println("Callable thread");
            return output;
        });

        try {
            System.out.println(futureObj.get().size());
        } catch (Exception e) {
            System.out.println("Exception in calling callable");
        }
    }
}
