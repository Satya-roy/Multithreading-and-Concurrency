// Single threaded
// Using bfs

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

public class Approach1 {
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
        List<Integer> pages = bfs(graph, 4, nodeHostNameMap);

        for(int pageNum : pages) {
            System.out.println(inputUrls[pageNum]);
        }
    }

    public List<Integer> bfs(Map<Integer, List<Integer>> graph, int startNode, Map<Integer, String> nodeHostNameMap) {
        List<Integer>ans = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        boolean []visited = new boolean[5];
        q.add(startNode);
        ans.add(startNode);

        while(!q.isEmpty()) {
            int currNode = q.poll();
            visited[currNode] = true;

            for(Integer v : graph.get(currNode)) {
                if(!visited[v] && nodeHostNameMap.get(startNode).compareTo(nodeHostNameMap.get(v))==0) {
                    ans.add(v);
                    q.add(v);
                }
            }
        }

        return ans;
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
        Approach1 webCrawler = new Approach1();
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
