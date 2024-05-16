//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        ProduceTask producer = new ProduceTask(sharedResource);
        ConsumeTask consumer = new ConsumeTask(sharedResource);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}