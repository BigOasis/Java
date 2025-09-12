import java.util.*;

/*
    [2번째 시도 : 80분 ] 2ms 44MB
      1. 방문하지 않았으면 -1로 채우기
      2. 방문 시작을 0, 연결된 노드를 1로 채우되, q에서 연결노드를 돌다가 나와 연결된 노드가 0 <-> 1로 다르게 있지 않고, 같으면 false
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
      int node  = graph.length;
      int[] color = new int[node];
      Arrays.fill(color,-1);

      for(int start = 0; start < node; start++){
        if(color[start] != -1) continue; //이미 방문했으면

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(start);
        color[start] = 0;

        while(!q.isEmpty()){
          int now = q.removeFirst();

          for(int next : graph[now]){
            // 아직 방문 X
            if(color[next] == -1){
              color[next] = color[now] ^ 1;
              q.addLast(next);
            }
            // 방문 했고, 연결된 노드와 같은 위치에 있으면 
            else if(color[next] == color[now]){
              return false;
            }
          }
        }
      }
      return true;
    }
}

import java.util.*;

/*
    [첫 시도 : 실패 60/85]
    처음에는 Set<int[]> -> 하지만 안의 값을 1,3으로 같아도 배열의 참조값이 달라서 다른 값으로 인식
    => Set<List<Integer>> 로 해야 함. int를 for문으로 돌면서 List에 넣고, set에 넣어야 함. 
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        Set<List<Integer>> set = new HashSet<>();

        for(int[] g : graph){
            List<Integer> list = new ArrayList<>();
            for(int i : g){
                list.add(i);
            }
            set.add(list);
        }
        System.out.println(set);
        return set.size() == 2;
    }
}