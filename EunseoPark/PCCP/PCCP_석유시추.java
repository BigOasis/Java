import java.util.*;

/*
    [스스로 풀이 : 효율성 1개 런타임 에러]
        자바에서도 재귀를 많이 호출하면 스택오버플로우 발생, dfs -> bfs로 변경
*/
class Solution {
    static boolean[][] visited;
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    static int N,M;
    
    public int solution(int[][] land) {
        N = land.length; M = land[0].length;
        visited = new boolean[N][M];
        Map<Integer, Integer> oilCount = new HashMap<>();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    int count = 1;
                    
                    Set<Integer> set = new HashSet<>();
                    Deque<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        set.add(cur[1]);
                        
                        for(int d = 0; d< 4; d++){
                            int ny = cur[0] + dy[d], nx = cur[1] + dx[d];
                            
                            if(!inRange(ny,nx)) continue;
                            if(land[ny][nx] == 1 && !visited[ny][nx]){
                                visited[ny][nx] = true;
                                count++;
                                q.add(new int[]{ny,nx});
                            }
                        }
                    }
                    
                    for(int x : set){
                        oilCount.put(x, oilCount.getOrDefault(x, 0) + count);
                    }
                }
            }
        }
        int answer = 0;
        for(int oil : oilCount.values()){
            answer = Math.max(answer, oil);
        }
        return answer;
    }
    
    static boolean inRange(int y, int x){
        return -1 < y && y < N && -1 < x && x < M;
    }
}