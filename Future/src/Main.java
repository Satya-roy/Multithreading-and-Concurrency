import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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


        // Callable
        Future<List<Integer>> futureObj2 = poolExecutor.submit(()->{
            List<Integer> output = new ArrayList<>();
            output.add(200);
            output.add(300);
            output.add(400);

            return output;
        });

        try {
            System.out.println(futureObj2.get().size());
        } catch (Exception e) {
            System.out.println("Exception during callable");
        }

    }
}