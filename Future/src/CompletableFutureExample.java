import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10));

    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        return "Create a completable future";
    }, poolExecutor);

    public void printValue() {
        try{
            System.out.println(completableFuture.get());
        } catch (Exception e) {

        }
    }
}
