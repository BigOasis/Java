import java.util.*;

/*
  [정답 참고] 9ms 46MB
    위상정렬인데 기억안남..
    1. Map<Integer,List<Integer>> 출발노드, 연결노드들 (출발노드 : 선행 노드, 연결 노드 : 선행 노드를 듣고 수강할 수 있는 노드)
    2. 연결노드가 각각 선행해서 들어야 할 노드의 개수 indegree

    3. indegree == 0 인 노드 : 리프노드
      q에 넣고, count(수강한 노드)++
    4. q에 넣고, 연결 노드 확인,
      indegree[연결노드]--   (선행 수강 노드들을 들었다는 거니까)
      선행 노드 다 들었으면 이제 q에 넣고, count++
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses+1]; //선행되어야 할 노드 개수

        for(int[] pre : prerequisites){
          graph.putIfAbsent(pre[1], new ArrayList<>());
          graph.get(pre[1]).add(pre[0]);
          indegree[pre[0]]++;
        }

        int count = 0; // 수강한 노드 개수
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i< numCourses; i++){
          if(indegree[i] == 0){
            q.addLast(i);
            count++;
          }
        }

        while(!q.isEmpty()){
          int cur = q.removeFirst();
          if(!graph.containsKey(cur)) continue;

          for(int next: graph.get(cur)){
            indegree[next]--;
            if(indegree[next] == 0) {
              count++;
              q.addLast(next);
            }
          }
        }
        return count == numCourses;
    }
}