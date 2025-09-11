import java.util.*;

/*
  [스스로 풀이 : 20분] 7ms 45MB
    1. S -> E 로 노드 연결하기
    2. degree == 0 인 리프노드를 q에 넣기
    3. degree == 0 이 되면 , 선행 노드를 모두 수강했다는 뜻이므로, q에 넣고 count++
    4. 모두 돌았다면 순서를, 모두 돌지 못했다면 빈 배열 반환
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[numCourses];
        for(int[] pre : prerequisites){
          graph.putIfAbsent(pre[1], new ArrayList<>());
          graph.get(pre[1]).add(pre[0]);
          degree[pre[0]]++;
        }

        Deque<Integer> q =  new ArrayDeque<>();
        int count = 0;
        int[] answer = new int[numCourses];

        for(int i = 0; i< numCourses; i++){
          if(degree[i] ==0){
            answer[count] = i;
            count++;
            q.addLast(i);
          }
        }

        while(!q.isEmpty()){
          int cur = q.removeFirst();

          if(!graph.containsKey(cur)) continue;
          for(int next : graph.get(cur)){
            degree[next]--;
            if(degree[next] == 0){
              answer[count] = next;
              count++;
              q.addLast(next);
            }
          }
        }
        if(count == numCourses){
          return answer;
        }
        return new int[]{};
    }
}