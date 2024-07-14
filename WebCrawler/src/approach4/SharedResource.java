package approach4;

import java.util.List;
import java.util.Set;

public class SharedResource {
    private final List<Integer> pages;
    private final Set<Integer> visited;

    public SharedResource(List<Integer> pages, Set<Integer> visited) {
        this.pages = pages;
        this.visited = visited;
    }

    public synchronized void addInVisited(int node) {
        visited.add(node);
    }

    public synchronized void addInPages(int node) {
        pages.add(node);
    }

    public synchronized boolean checkInVisited(int node) {
        return visited.contains(node);
    }

    public List<Integer> getPages() {
        return pages;
    }
}
