import sharedResource.Resource;

public class ReadTheListTask implements Runnable {
    Resource resource;

    public ReadTheListTask(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for(Integer i : resource.getListResource()) {
            try {
                Thread.sleep(5000);
                System.out.println("Print via thread " + Thread.currentThread().getName() + ":" + i);
            } catch (Exception e) {
                //handle exception
            }

        }
    }
}
