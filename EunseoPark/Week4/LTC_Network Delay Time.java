import java.util.*;
/*
  [스스로 풀이 : 60분 , 실패]
    1. (k,0) 로 q에 넣기
    2. (!visited[next]) 면 visit ++ 하고, q에 (next, cur + weight)로 넣가
    3. weight 값 계속 업데이트
    => visit로 하다보니, 이미 visit하면서 최솟값을 같이 구하려다 보니, 아직 방문안했지만, 큐에 들어가서 visit는 다 방문한 것으로 나와서 실제 weight보다 작은 값으로 결과 나옴.

  [정답 참고] 10ms 49MB
    1. 최소 거리 나오면 거리 업데이트 후
    2. 모든 노드를 다 방문하지 않았다면
    3. 각 노드의 거리는 해당 노드까지 도달하는데 걸린 최소 거리이므로 그 중 가장 큰 값이 모두 지나간 거리

*/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
      List<List<int[]>> graph = new ArrayList<>();
      for(int i = 0; i<= n; i++){
        graph.add(new ArrayList<>());
      }
      for(int[] time : times){
        int s = time[0], e = time[1], w = time[2];
        graph.get(s).add(new int[]{e,w});
      }

      int[] dist = new int[n+1];
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[k] = 0;

      // 거리 오름차순
      PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
      pq.offer(new int[]{k,0});

      while(!pq.isEmpty()){
        int[] cur = pq.poll();
        int node = cur[0], weight = cur[1];

        for(int[] next : graph.get(node)){
          int nextNode = next[0], nextWeight = next[1];
          if(dist[nextNode] > weight + nextWeight){
            dist[nextNode] = weight + nextWeight;
            pq.offer(new int[]{nextNode, dist[nextNode]});
          }
        }
      }

      int result = 0;
      for(int i = 1; i <= n; i++){
        if(dist[i] == Integer.MAX_VALUE){
          return -1;
        }
        result = Math.max(result, dist[i]);
      }

      return result;
    }
}