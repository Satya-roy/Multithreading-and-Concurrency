import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.allowCoreThreadTimeOut(true);

        //submit task

        for(int i=0; i<25; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(4000);
                    System.out.println("Thread name: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    //handle exception
                }
            });
        }
    }
}