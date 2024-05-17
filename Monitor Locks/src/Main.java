//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //all 3 threads working on a same object/resource
        MonitorLockExample obj = new MonitorLockExample();

        Thread thread1 = new Thread(new MyClass(obj));
        Thread thread2 = new Thread(obj::task2);
        Thread thread3 = new Thread(() -> obj.task3());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}