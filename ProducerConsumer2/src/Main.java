//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producerThread1 = new Thread(new ProducerTask(sharedResource, 15));
        Thread consumerThread2 = new Thread(new ConsumerTask(sharedResource));

        consumerThread2.start();
        producerThread1.start();
    }
}