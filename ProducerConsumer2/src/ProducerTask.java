public class ProducerTask implements Runnable{
    private final SharedResource sharedResource;
    private int val;

    public ProducerTask(SharedResource sharedResource, int val) {
        this.sharedResource = sharedResource;
        this.val = val;
    }

    @Override
    public void run() {
        for(int i=0; i<val; i++) {
            sharedResource.addItem(i);
        }
    }
}
