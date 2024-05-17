public class SharedResource {
    private boolean itemAvailable;

    //addItem - produce item
    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("Item added by: " + Thread.currentThread().getName() + " and invoking all threads which are in wait state");
        notifyAll(); // all the threads that are waiting on this object, invoke
    }

    //consumeItem
    public synchronized void consumeItem() {
        //using while loop to avoid "spurious wake-up", sometimes because of system noise
        //do not use if, as if a thread is accidentally woke up, then it will be in while loop and will again go to wait state
        //so while waiting always use while loop
        while(!itemAvailable) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting now");
                wait(); //releases the monitor lock
            } catch (Exception e) {
                //handle exception
            }
        }

        System.out.println("Item consumed by: " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}
