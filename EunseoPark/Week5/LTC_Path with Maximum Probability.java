import java.util.*;

/*
  [최적화 참고] 24ms 57MB
    1. PQ로 일찍 탐색
    2. Double 인 프로퍼티 비교는 Double.compare(a,b)
*/
class Solution {

    static class Node implements Comparable<Node>{
      int n;
      double w;

      public Node(int n, double w){
        this.n = n; this.w = w;
      }
      public int compareTo(Node o){
        return Double.compare(o.w, this.w);
      }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Node>> graph = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
          graph.add(new ArrayList<>());
        }
        
        int len = edges.length;
        for(int i =0; i < len; i++){
          int s = edges[i][0], e = edges[i][1];
          graph.get(s).add(new Node(e, succProb[i]));
          graph.get(e).add(new Node(s, succProb[i]));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start_node, 1.0));
        double[] dist = new double[n];
        Arrays.fill(dist, -1.0);

        while(!q.isEmpty()){
          Node cur = q.poll();

          if(cur.n == end_node) return dist[cur.n];

          if(cur.w < dist[cur.n]) continue;

          for(Node next : graph.get(cur.n)){
            if(dist[next.n] < cur.w * next.w){
              dist[next.n] = cur.w * next.w;
              q.add(new Node(next.n, dist[next.n]));
            }
          }
        }
        return 0.0;
    }
}

import java.util.*;

/*
  [스스로 풀이 : 30분] 29ms 56MB
*/
class Solution {

    static class Node{
      int n;
      double w;

      public Node(int n, double w){
        this.n = n; this.w = w;
      }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Node>> graph = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
          graph.add(new ArrayList<>());
        }
        
        int len = edges.length;
        for(int i =0; i < len; i++){
          int s = edges[i][0], e = edges[i][1];
          graph.get(s).add(new Node(e, succProb[i]));
          graph.get(e).add(new Node(s, succProb[i]));
        }

        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(start_node, 1.0));
        double[] dist = new double[n];
        Arrays.fill(dist, -1.0);

        while(!q.isEmpty()){
          Node cur = q.poll();

          if(cur.w < dist[cur.n]) continue;

          for(Node next : graph.get(cur.n)){
            if(dist[next.n] == next.w) continue;
            if(dist[next.n] < cur.w * next.w){
              dist[next.n] = cur.w * next.w;
              q.add(new Node(next.n, dist[next.n]));
            }
          }
        }
        return dist[end_node] == -1.0 ? 0.0 : dist[end_node];
    }
}