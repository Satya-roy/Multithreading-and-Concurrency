//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Task: Increment the counter 400 times
        ShareResourceThreadSafe resource1 = new ShareResourceThreadSafe();

        Thread th1 = new Thread(() -> {
            for(int i=0; i<200; i++) {
                resource1.increment();
            }
        });

        Thread th2 = new Thread(() -> {
            for(int i=0; i<200; i++) {
                resource1.increment();
            }
        });

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch(Exception e) {
            //handle exception
        }

        System.out.println(resource1.get());
    }
}