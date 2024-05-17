public class MonitorLockExample {
    public synchronized void task1() {
        try {
            System.out.println("inside task1");
            Thread.sleep(10000);
        } catch (Exception e) {
            //handle exception
        }
    }

    public void task2() {
        System.out.println("inside task2, before synchronized");
        synchronized (this) {
            System.out.println("task2, inside synchronized");
        }
    }

    public void task3() {
        System.out.println("inside task3");
    }
}
