import java.util.*;

/*
		[스스로 풀이 : 80분]
    트리를 이동하면서, (양 - 늑대) 가 가장 큰 경우부터 움직이는 가장 많이 움직일 수 있음 -> PQ
        [인덱스, (양 - 늑대)] 배열을 PQ에 넣고 (양-늑대) 내림차순
    PQ에 넣을 때 visited도 같이 넣기
*/
class Solution {
    
    class node implements Comparable<node>{
        int index, wolf, sheep;
        boolean[] visited;
        
        public node(int index, int wolf, int sheep, boolean[] v){
            this.index = index; this.wolf = wolf; this.sheep = sheep;
            
            this.visited = Arrays.copyOf(v, v.length);
        }
        
        public int compareTo(node other){
            if(other.sheep == this.sheep){
                return other.wolf - this.wolf;
            }
            return other.sheep - this.sheep;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        int n = info.length;
        
        for(int i = 0 ; i< n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge : edges){
            int s = edge[0], e = edge[1];
            graph.get(s).add(e);
        }
        PriorityQueue<node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        pq.add(new node(0,0,1,visited)); // 인덱스, 늑대, 양
        
        while(!pq.isEmpty()){
            node cur = pq.poll();
            answer = Math.max(answer, cur.sheep);
            
            for(int i = 0 ; i < n; i++){
                if(cur.visited[i]){
                    for(int next : graph.get(i)){
                        if(!cur.visited[next]){
                            int wolf = 0; int sheep = 0;
                            if(info[next] == 1){
                                wolf++;
                            }
                            else{
                                sheep++;
                            };
                            if(cur.wolf + wolf < cur.sheep + sheep){
                                cur.visited[next] = true;
                                pq.add(new node(next, cur.wolf+wolf, cur.sheep + sheep, cur.visited));
                                cur.visited[next] = false;
                            }
                        }
                    }
                }
            }
        }        
        
        
        return answer;
    }
}