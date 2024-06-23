import sharedResource.Resource;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Resource resource = new Resource();

        resource.getListResource().add(1);
        resource.getListResource().add(2);
        resource.getListResource().add(3);
        resource.getListResource().add(4);

        Thread th1 = new Thread(new ReadTheListTask(resource));

        th1.start();
        // This will throw concurrentModificationException
        
        // Main thread is modifying the list
        // Thread-0: iterating through the list
        resource.getListResource().add(5);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {}
        resource.getListResource().add(6);
        resource.getListResource().add(7);
    }
}