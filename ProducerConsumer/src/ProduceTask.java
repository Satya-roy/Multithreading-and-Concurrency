public class ProduceTask implements Runnable{
    private final SharedResource sharedResource;
    public ProduceTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000); // timed-waiting state doesn't release the monitor lock
        } catch (Exception e) {
            //handle exception
        }
        sharedResource.addItem();
    }
}
