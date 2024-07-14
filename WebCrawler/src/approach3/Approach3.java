package approach3;

// Multi thread, synchronized block
// DFS

//Input:
//urls = [
//        "http://news.yahoo.com",
//        "http://news.yahoo.com/news",
//        "http://news.yahoo.com/news/topics/",
//        "http://news.google.com",
//        "http://news.yahoo.com/us"
//        ]
//edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
//startUrl = "http://news.yahoo.com/news/topics/"
//Output: [
//        "http://news.yahoo.com",
//        "http://news.yahoo.com/news",
//        "http://news.yahoo.com/news/topics/",
//        "http://news.yahoo.com/us"
//        ]

import java.util.*;

public class Approach3 {
    private final String[] inputUrls = new String[] {
            "http://news.yahoo.com",
            "http://news.yahoo.com/news",
            "http://news.yahoo.com/news/topics/",
            "http://news.google.com",
            "http://news.yahoo.com/us"
    };
    private final int [][] edges = new int[][] {{2,0},{2,1},{3,2},{3,1},{0,4}};


    public void webCrawler(SharedResource sharedResource) {
        List<String[]> input = changeInputFormat();
        //printInputFormat(input);
        Map<Integer, String> nodeHostNameMap = new HashMap<>();
        int i = 0;

        for(String[] parts : input) {
            nodeHostNameMap.put(i, parts[1]);
            i++;
        }

        Map<Integer, List<Integer>> graph = createGraph();

//        List<Integer> pages = sharedResource.getPages();
//        Set<Integer> visited = sharedResource.getVisited();

        dfs(graph, 3, nodeHostNameMap, sharedResource);

//        for(int pageNum : sharedResource.getPages()) {
//            System.out.println(inputUrls[pageNum]);
//        }
    }
    private void dfs(Map<Integer, List<Integer>> graph, int node, Map<Integer, String> nodeHostNameMap, SharedResource sharedResource) {
        sharedResource.addInPages(node);
        sharedResource.addInVisited(node);
        System.out.println(inputUrls[node] + " " + Thread.currentThread());
        List<Thread> tasks = new ArrayList<>();
        for(Integer v : graph.get(node)) {
            if(!sharedResource.checkInVisited(v) && nodeHostNameMap.get(node).compareTo(nodeHostNameMap.get(v))==0) {
                tasks.add(new Thread(() -> {
                    dfs(graph, v, nodeHostNameMap, sharedResource);
                }));
            }
        }

        for(Thread thread : tasks) {
            thread.start();
        }

        for(Thread thread : tasks) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<String[]> changeInputFormat() {
        List<String[]> result = new ArrayList<>();

        for(String url : inputUrls) {
            String[] urlPart = url.split("/+");
            result.add(urlPart);
        }
        return result;
    }
    private Map<Integer, List<Integer>> createGraph() {
        Map<Integer, List<Integer>> res = new HashMap<>();
        for(int i=0; i<=4; i++) {
            res.put(i, new ArrayList<>());
        }
        for(int[] edge: edges) {
            res.get(edge[0]).add(edge[1]);
        }
        return res;
    }

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(new ArrayList<>(), new HashSet<>());
        Approach3 webCrawler = new Approach3();
        webCrawler.webCrawler(sharedResource);
    }

    private void printInputFormat(List<String[]> input) {
        for(String[] urlParts : input) {
            for (String urlPart : urlParts) {
                System.out.println(urlPart + " ");
            }
            System.out.println();
        }
    }
}
