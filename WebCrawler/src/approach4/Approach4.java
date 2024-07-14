package approach4;
import approach3.SharedResource;

import java.util.*;
import java.util.concurrent.*;

// Multi Thread
// Using dfs - threadPoolExecutor

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


public class Approach4 {
    private final String[] inputUrls = new String[] {
            "http://news.yahoo.com",
            "http://news.yahoo.com/news",
            "http://news.yahoo.com/news/topics/",
            "http://news.google.com",
            "http://news.yahoo.com/us"
    };
    private final int [][] edges = new int[][] {{2,0},{2,1},{3,2},{3,1},{0,4}};


    public void webCrawler() {
        List<String[]> input = changeInputFormat();
        //printInputFormat(input);
        Map<Integer, String> nodeHostNameMap = new HashMap<>();
        int i = 0;

        for(String[] parts : input) {
            nodeHostNameMap.put(i, parts[1]);
            i++;
        }

        Map<Integer, List<Integer>> graph = createGraph();
        SharedResource sharedResource = new SharedResource(new ArrayList<>(), new HashSet<>());
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(4, 6, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
        dfs(graph, 2, nodeHostNameMap, sharedResource, threadPoolExecutor);
        threadPoolExecutor.shutdown();
        for(int pageNum : sharedResource.getPages()) {
            System.out.println(inputUrls[pageNum]);
        }
    }
    private void dfs(Map<Integer, List<Integer>> graph, int node, Map<Integer, String> nodeHostNameMap, SharedResource sharedResource, ExecutorService threadPoolExecutor) {
        sharedResource.addInVisited(node);
        sharedResource.addInPages(node);
        List<Future<?>> futures = new ArrayList<>();
        for(Integer v : graph.get(node)) {
            if(!sharedResource.checkInVisited(v) && nodeHostNameMap.get(node).compareTo(nodeHostNameMap.get(v))==0) {
                futures.add(threadPoolExecutor.submit(() -> {
                    dfs(graph, v, nodeHostNameMap, sharedResource, threadPoolExecutor);
                }));
            }
        }
        try {
            for(Future<?> future : futures) {
                future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        Approach4 webCrawler = new Approach4();
        webCrawler.webCrawler();
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
