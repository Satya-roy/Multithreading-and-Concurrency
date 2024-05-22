public class ShareResourceNotThreadSafe {
    int counter;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }
}
