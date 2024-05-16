//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Step 3. Create an instance of class that implement 'Runnable'
        //Step 4. Pass the runnable object to the Thread constructor
        //Step 5. Start the thread

        System.out.println("Going inside the main method " + Thread.currentThread().getName());

        MyClass obj1 = new MyClass();
        Thread thread1 = new Thread(obj1);
        Thread thread2 = new Thread(obj1);
        thread1.start();
        thread2.start();
        System.out.println("Finish the main method " + Thread.currentThread().getName());
    }
}