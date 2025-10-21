import java.util.*;

/*
    [정답 참고]
        출발 -> 산봉우리 -> 출발의 경로 중에 최소 intensity인 경로를 찾아야 함.
        출발 -> 산봉우리의 경로대로 다시 산봉우리 -> 출발 방향으로 가야 가장 해당 경로는 최소 intensity가 됨.
        그럼 ['출발 -> 산봉우리' 경로 자체의 최대 intensity]가 최소가 되는 경로 찾기
        
        1. 양수 가중치이므로 다익스트라, 다익스트라에서는 visited[]로 해당 노드까지 최소 경로를 보통 저장, 
        마찬가지로 visited에는 그 경로 중 최대 intensity를 저장하고, 사이클 방지
        3. pq에 gate 노드를 모두 추가하고, visited = 0으로 초기화
        
*/
class Solution {
    static final int INF = Integer.MAX_VALUE;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Arrays.sort(summits); //최소 intensity가 같은 경우, 산봉우리가 작은 것으로 반환 위해 
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] path : paths){
            int s = path[0], e = path[1], w = path[2];
            graph.get(s).add(new int[]{e, w});
            graph.get(e).add(new int[]{s, w});
        }
        Set<Integer> summitSet = new HashSet<>();
        
        for(int summit : summits){
            summitSet.add(summit);
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)-> a[1]-b[1]); //intensity 오름차순
        int[] visited = new int[n + 1]; // 경로 중 최대 intensity 저장
        Arrays.fill(visited, INF);
        for(int gate : gates){
            q.add(new int[]{gate,0});
            visited[gate] = 0;
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0], w = cur[1];
            
            // 더 intensity가 큰 경로로 가거나 -> 최소 intensity를 찾아야 하니까 
            // 산봉우리에 도착
            if(visited[node] < w || summitSet.contains(node)) continue;
            
            for(int[] next: graph.get(node)){
                int nextNode = next[0], nextW = Math.max(next[1],w);
                
                if(visited[nextNode] > nextW){
                    visited[nextNode] = nextW;
                    q.add(new int[]{nextNode, nextW});
                }
            }
        }
        int[] answer = new int[2];
        int min = INF;
        
        for(int summit : summits){
            if(visited[summit] < min){
                answer[0] = summit;
                answer[1] = visited[summit];
                min = visited[summit];
            }
        }
        return answer;
    }
}