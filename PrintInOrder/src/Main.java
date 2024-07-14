//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Runnable printFirst = () -> System.out.println("first");
        Runnable printSecond = () -> System.out.println("Second");
        Runnable printThird = () -> System.out.println("Third");

        Foo foo = new Foo();

        Thread th1 = new Thread(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread th2 = new Thread(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread th3 = new Thread(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}