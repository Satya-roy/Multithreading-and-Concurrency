public class MyClass implements Runnable{
    MonitorLockExample monitorLockExample;

    public MyClass(MonitorLockExample monitorLockExample) {
        this.monitorLockExample = monitorLockExample;
    }
    @Override
    public void run() {
        monitorLockExample.task1();
    }
}
