import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of( 3, 5, 7, 9);
        int target = 11;

        int lowerBoundIndex = Collections.binarySearch(list, target);
        if (lowerBoundIndex >= 0) {
            // Element found
            System.out.println("Lower bound index of " + target + " is " + lowerBoundIndex);
        } else {
            // Element not found, calculate insertion point
            int insertionPoint = -(lowerBoundIndex + 1);
            System.out.println("Lower bound index of " + target + " is " + insertionPoint);
        }
    }
}
