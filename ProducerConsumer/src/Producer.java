public class Producer implements Runnable{
    private final SharedResource sharedResource;
    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //handle exception
        }
        sharedResource.addItem();
    }
}
