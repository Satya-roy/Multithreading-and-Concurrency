import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private int currNumber;
    private int currNumberToPrinted;
    private Lock lock = new ReentrantLock();
    private Condition semZero = lock.newCondition();
    private Condition semEven = lock.newCondition();
    private Condition semOdd = lock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.currNumber = 1;
        this.currNumberToPrinted = 0;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while(currNumberToPrinted<=n) {
            while(currNumberToPrinted !=0) {
                semZero.await();
            }

            if(currNumber<=n) {
                printNumber.accept(0);
            }
            currNumberToPrinted = currNumber;

            if(currNumber%2 == 0) {
                semEven.signal();
            } else {
                semOdd.signal();
            }
        }
        lock.unlock();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while(currNumber<=n) {
            while(currNumberToPrinted==0 || currNumberToPrinted%2 != 0) {
                semEven.await();
            }
            if(currNumberToPrinted <= n) {
                printNumber.accept(currNumberToPrinted);
            }
            currNumber++;
            currNumberToPrinted=0;

            semZero.signal();
        }
        lock.unlock();
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while(currNumber<=n) {
            while(currNumberToPrinted==0 || currNumberToPrinted%2!=1) {
                semOdd.await();
            }
            if(currNumberToPrinted<=n) {
                printNumber.accept(currNumberToPrinted);
            }
            currNumber++;
            currNumberToPrinted=0;

            semZero.signal();
        }
        lock.unlock();
    }
}