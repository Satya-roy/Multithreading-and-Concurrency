public class ConsumerTask implements Runnable {
    private final SharedResource sharedResource;

    public ConsumerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        for(int i=0; i<15; i++) {
            sharedResource.consumeItem();
        }

    }
}
