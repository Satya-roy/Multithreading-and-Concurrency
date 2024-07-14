public class Foo {
    boolean isFirstPrinted;
    boolean isSecondPrinted;

    public Foo() {
        this.isFirstPrinted = false;
        this.isSecondPrinted = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        isFirstPrinted = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while(!isFirstPrinted) {
            wait();
        }
        printSecond.run();
        isSecondPrinted = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while(!isSecondPrinted) {
            wait();
        }
        printThird.run();
    }
}