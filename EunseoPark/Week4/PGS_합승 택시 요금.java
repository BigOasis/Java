import java.util.*;

/*
    양방향이므로 S,A,B를 출발점
    X를 합승하는 점으로 할 때
    S - X, X - A, X - B
    (S->X) + (A->X) + (B->X) 까지의 최소거리 
    출발점을 for문으로 S,A,B로 하고 dist 를 기록하는 배열도 3개, 
    n 돌면서 dist[0][X] + dist[1][X] + dist[2][X] 의 최소 
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] fare : fares){
            graph.get(fare[0]).add(new int[]{fare[1],fare[2]});
            graph.get(fare[1]).add(new int[]{fare[0],fare[2]});
        }
        
        int[] node = new int[]{s,a,b};
        int[][] dist = new int[3][n+1];
        
        for(int i = 0; i < 3; i++){
            int start = node[i];
            
            PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[1]-y[1]);
            pq.add(new int[]{start, 0});
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][start] = 0;
            
            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int curNode = cur[0], curWeight = cur[1];
                
                if(dist[i][curNode] < curWeight) continue;
                
                for(int[] next : graph.get(curNode)){
                    int nextNode = next[0], nextWeight = next[1];
                    if(dist[i][nextNode] > curWeight + nextWeight){
                        dist[i][nextNode] = curWeight + nextWeight;
                        pq.add(new int[]{nextNode, dist[i][nextNode]});
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, dist[0][i] + dist[1][i] + dist[2][i]);
        }
        return answer;
    }
}