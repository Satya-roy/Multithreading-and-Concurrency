import hydrogen.ReleaseHydrogen;
import oxygen.ReleaseOxygen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        H2O h2o = new H2O();
        ReleaseHydrogen releaseHydrogen = new ReleaseHydrogen();
        ReleaseOxygen releaseOxygen = new ReleaseOxygen();
        ExecutorService threadPool = Executors.newFixedThreadPool(9);

        for(int i=0; i<6; i++) {
            threadPool.execute(() -> {
                try {
                    h2o.hydrogen(releaseHydrogen);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for(int i=0; i<3; i++) {
            threadPool.execute(() -> {
                try {
                    h2o.oxygen(releaseOxygen);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        threadPool.shutdown();
    }
}