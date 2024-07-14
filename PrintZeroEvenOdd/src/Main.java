import java.util.function.IntConsumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IntConsumer printNumber = (number) -> System.out.print(number + " ");

        int n = 10;

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);

        Thread zeroThread = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread evenThread = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread oddThread = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        zeroThread.start();
        oddThread.start();
        evenThread.start();

        try {
            zeroThread.join();
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}