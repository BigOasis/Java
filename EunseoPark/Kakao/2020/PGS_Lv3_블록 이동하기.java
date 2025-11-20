import java.util.*;

/*
    중복 방문 탐색을 없애기 위해 visited를 하려고 했는데, 매 방문마다 visited를 만들기보다, H를 1, V를 2로 해서 더하면서 중복 제거
*/

class Solution {
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    
    public int solution(int[][] board) {
        int N = board.length;
        
        int[][] visited = new int[N][N];
        visited[0][0] = 1;
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1,0}); //y,x,type,count
        
        while(!q.isEmpty()){
            
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], type = cur[2], count = cur[3];
            
            if((type == 1 && y == N - 1 && x == N-2) || (type == 2 && y == N-2 && x == N-1)){
                return count;
            }
            
            if(type == 1){
                if(inRange(y-1,x,N) && inRange(y-1,x+1,N) && board[y-1][x] == 0 && board[y-1][x+1]==0 ){
                    if(visited[y-1][x] < 2){
                        q.add(new int[]{y-1,x,2,count+1});                    
                        visited[y-1][x] += 2;
                    }
                    if(visited[y-1][x+1] < 2){                        
                        q.add(new int[]{y-1,x+1,2,count+1});
                        visited[y-1][x+1] += 2;
                    }
                    if(visited[y-1][x] % 2 == 0){
                        q.add(new int[]{y-1,x,1,count+1});
                        visited[y-1][x] += 1;
                    }
                }
                if(inRange(y,x-1,N) && (visited[y][x-1] %2 == 0) && board[y][x-1] == 0){
                    q.add(new int[]{y,x-1,type,count+1});
                    visited[y][x-1] += 1;
                }
                if(inRange(y,x+2,N) && (visited[y][x+1] %2 == 0) && board[y][x+2] == 0){
                    q.add(new int[]{y,x+1,type,count+1});
                    visited[y][x+1] += 1;
                }
                if(inRange(y+1,x,N) && inRange(y+1,x+1,N) && board[y+1][x] == 0 && board[y+1][x+1] == 0){
                    if(visited[y][x] < 2){
                        q.add(new int[]{y,x,2,count+1});                    
                        visited[y][x] += 2;
                    }
                    if(visited[y][x+1] < 2){                        
                        q.add(new int[]{y,x+1,2,count+1});
                        visited[y][x+1] += 2;
                    }
                    if(visited[y+1][x] %2 ==0){
                        q.add(new int[]{y+1,x,1,count+1});
                        visited[y+1][x] += 1;
                    }
                }
                
            }else{
                if(inRange(y,x-1,N) && inRange(y+1,x-1,N) && board[y][x-1] == 0 && board[y+1][x-1] == 0){
                    if(visited[y][x-1] % 2 == 0 ){
                        q.add(new int[]{y,x-1,1,count+1});
                        visited[y][x-1] += 1;
                    }
                    if(visited[y+1][x-1] % 2 == 0){
                        q.add(new int[]{y+1,x-1,1,count+1});
                        visited[y+1][x-1] += 1;
                    }
                    if(visited[y][x-1] < 2){
                        q.add(new int[]{y,x-1,2,count+1});
                        visited[y][x-1] += 2;
                    }
                }
                if(inRange(y-1,x,N) && visited[y-1][x] < 2 && board[y-1][x] == 0){
                    q.add(new int[]{y-1,x,2, count+1});
                    visited[y-1][x] += 2;
                }
                if(inRange(y+2,x,N) && visited[y+1][x] < 2 && board[y+2][x] == 0){
                    q.add(new int[]{y+1,x,2,count+1});
                    visited[y+1][x] += 2;
                }
                if(inRange(y,x+1,N) && inRange(y+1,x+1,N) && board[y][x+1] == 0 && board[y+1][x+1] == 0){
                    if(visited[y][x] % 2 == 0){
                        q.add(new int[]{y,x,1,count+1});
                        visited[y][x] += 1;
                    }
                    if(visited[y+1][x] % 2 == 0){
                        q.add(new int[]{y+1,x,1,count+1});
                        visited[y+1][x] += 1;
                    }
                    if(visited[y][x+1] < 2){
                        q.add(new int[]{y,x+1,2, count+1});
                        visited[y][x+1] += 2;
                    }
                }
                
            }
        }
        
        return -1;
    }
    static boolean inRange(int y, int x, int N){
        return -1 < y && y < N && -1 < x && x < N;
    }
}