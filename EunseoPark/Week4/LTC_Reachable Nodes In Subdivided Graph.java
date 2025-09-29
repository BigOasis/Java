import java.util.*;

/*
  [정답 참고] 21ms 50MB
    문제 이해 어려웠음. 중간 노드가 아닌 가중치로 생각하고, 각 위치까지 최단 경로는 생각했는데, 
    남은 칸수만큼 왼쪽, 오른쪽에서 오는거는 생각 못함
*/
class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0;i <n; i++){
          graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
          int s = edge[0], e = edge[1], w = edge[2] + 1;
          graph.get(s).add(new int[]{e,w});
          graph.get(e).add(new int[]{s,w});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});
        dist[0] = 0;

        // 노드까지 최단거리 구하기
        while(!pq.isEmpty()){
          int[] cur = pq.poll();
          int node = cur[0], w = cur[1];
          if(dist[node] < w) continue;

          for(int[] next: graph.get(node)){
            int nextNode = next[0], nextW = next[1];

            if(dist[nextNode] > w + nextW) {
              dist[nextNode] = w + nextW;
              pq.add(new int[]{nextNode, dist[nextNode]});
            }
          }
        }

        int sum = 0;
        //이동한 노드 개수 세기
        for(int i = 0; i <n; i++){
          if(dist[i] <= maxMoves) sum++;
        }
        //지나간 도시 노드 개수 세기
        for(int[] edge : edges){
          int s = edge[0], e = edge[1], w = edge[2];
          int l = Math.max(0, maxMoves - dist[s]); //왼쪽에서 남은 이동 가능 수
          int r = Math.max(0, maxMoves - dist[e]); //오른쪽에서 남은 이동 가능 수
          int u = Math.min(w, l + r); //겹쳐도 w를 넘지 않게
          sum += u;
        }
        return sum;
    }
}