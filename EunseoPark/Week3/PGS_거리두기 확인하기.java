import java.util.*;

/*
    [스스로 풀이 : 약 50분] 
        P일때 0,1,2 거리 순으로 멀어져가면서 확인, 
        
        종료 조건:
            1. 거리가 3부터는 continue
            2. P이면 거리 제대로 안 지켰다는 소리 -> return 0
            
        방문 조건:
            이때 X이면 막혀있어서 더 못가니까 q에 넣지 않고, 
                ( 범위 내 && !방문 ) 이면 넣기
        
*/
class Solution {
    int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i = 0 ; i < 5; i++){
            answer[i] = checkDistance(places[i]);
        }
        return answer;
    }
    private int checkDistance(String[] place){

        
        for(int i = 0; i< 5; i ++){
            for(int j = 0; j < 5; j++){
                
                if(place[i].charAt(j) == 'P'){
                    Deque<int[]> q = new ArrayDeque<>();
                    boolean[][] visited = new boolean[5][5];
                    q.add(new int[]{i,j,0});
                    visited[i][j] = true;
                    
                    while(!q.isEmpty()){
                        int[] cur = q.removeFirst();
                        int y = cur[0], x = cur[1], count = cur[2];
                        
                        if(count > 2) continue;
                        if(count !=0 && place[y].charAt(x) =='P') return 0;
                        
                        for(int d = 0; d < 4; d++){
                            int ny = y + dy[d], nx = x + dx[d];
                           
                            if(inRange(ny,nx) && !visited[ny][nx]){
                                if(place[ny].charAt(nx) =='X') continue;
                                visited[ny][nx] = true;
                                q.addLast(new int[]{ny,nx,count+1});
                            }
                        }
                    }
                }
            }
        }
        
        return 1;
    }
    private boolean inRange(int y, int x){
        return -1 < y && y < 5 && -1 < x && x < 5;
    }
}