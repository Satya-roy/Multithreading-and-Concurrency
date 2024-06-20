package task;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTask extends RecursiveTask<Integer> {
    int start;
    int end;
    public ComputeSumTask(int _start, int _end) {
        this.start = _start;
        this.end = _end;
    }

    @Override
    public Integer compute() {
        //base case
        if(end-start <= 4) {
            int totalSum = 0;
            for (int i = start; i <= end; i++) {
                totalSum += i;
            }
            return totalSum;
        } else {
            //split task
            int mid = (start+end)/2;
            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid+1, end);

            //fork the task for parallel execution
            leftTask.fork();
            rightTask.fork();

            //Combine the results of subtask
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //combine left and right result
            return leftResult + rightResult;
        }
    }
}
