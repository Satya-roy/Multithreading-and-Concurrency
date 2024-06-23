package sharedResource;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    List<Integer> listResource;

    public Resource() {
        this.listResource = new ArrayList<>();
    }

    public List<Integer> getListResource() {
        return listResource;
    }
}
