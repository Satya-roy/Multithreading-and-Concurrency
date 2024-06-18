import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureExample {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10));

        Future<?> futureObj1 = poolExecutor.submit(() -> {
            try{
                Thread.sleep(5000);
            } catch (Exception e){
                System.out.println("Exception in main thread");
            }

            System.out.println("This is the task, that will be executed by thread");
        });

        try {
            futureObj1.get();
        } catch (Exception e) {
            System.out.println("Found exception");
        }
    }
}
