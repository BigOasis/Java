import java.util.*;

class Solution {
    
    static int N, M;
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    
    public int solution(String[] maps) {
        N = maps.length; M = maps[0].length();
        
        int startY = 0, startX = 0;
        int middleY = 0, middleX = 0;
        int exitY = 0, exitX = 0;
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j) == 'S'){
                    startY = i; startX = j;
                }
                else if(maps[i].charAt(j) == 'L'){
                    middleY = i; middleX = j;
                }
                else if(maps[i].charAt(j) == 'E'){
                    exitY = i; exitX = j;
                }
            }
        }
        
        int start = bfs(startY, startX, middleY, middleX, maps);
        if(start < 0) return -1;
        
        int second = bfs(middleY, middleX, exitY, exitX, maps);
        if(second < 0) return -1;
        
        return start + second;
    }
    
    static int bfs(int sy, int sx, int desty, int destx, String[] maps){
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sy, sx, 0});
        boolean[][] visited = new boolean[N][M];
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], cnt = cur[2];

            for(int d = 0; d <4; d++){
                int ny = y + dy[d], nx = x + dx[d];
                
                if(inRange(ny,nx) && maps[ny].charAt(nx)!='X' && !visited[ny][nx]){
                    if(ny == desty && nx == destx) return cnt + 1;
                    
                    q.add(new int[]{ny,nx,cnt+1});
                    visited[ny][nx] = true;
                }
            }
            
        }
        return -1;
    }
    static boolean inRange(int y, int x){
        return -1 < y && y < N && -1 < x && x < M;
    }
}