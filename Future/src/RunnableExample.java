import java.util.List;

public class RunnableExample implements Runnable{
    private List<Integer> output;

    public RunnableExample(List<Integer> output) {
        this.output = output;
    }

    @Override
    public void run() {
        output.add(10);
        output.add(20);
        output.add(30);
    }
}
