import java.util.*;

/*
    *내 감상 : 연쇄적인 퍼짐으로 봤을 때 '백준 연구소' 문제와 유사한 것 같다. 그런데 중간에 구멍 뚫린 거 감안하는 거 플러스 되서 더 어려운 버전 
    [스스로 풀이 오답]
        1. len이 1일때, 2일때를 나누고 진행, 한 단계에서 모두 제거한 후 다음 라운드에 일괄 적용한다.
        2. 이때 중간에 구멍이 났는데, boundary 지우면서 파고들다가 구멍 만나서 구멍 상하좌우 방문 가능하도록 연쇄 업데이트를 놓쳤다
    
    [2차 시도 : 다른 사람 풀이 참고 후 다시 풀어보기]
        1. len이 1, 2일때 지우고 나서
        2. boundary를 업데이트한다. 
            이미 지워지고, 방문 가능한 것들 (boundary에서 지워진 것들) 부터 시작해서
                if 상하좌우가 방문 가능이 아니면, 
                    방문 가능하게 만들기
                    if 이미 지워진 것(지워졌는데, 방문 가능이 아니었다 -> 구멍)
                        q에 넣고 연쇄 퍼트리기
        
        bfs로 연쇄 퍼트리기 할때 visited 로 중복 방문 안해줘서 무한 실행됨
*/
class Solution {
    static int n,m;
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length; m = storage[0].length();
        boolean[][] removed = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < m; i++){
            visited[0][i] = true;
            visited[n-1][i] = true;
        }
        for(int i = 1; i < n -1; i++){
            visited[i][0] = true; 
            visited[i][m-1] = true;
        }

        for(String request : requests){
            int len = request.length();
            if(len == 1){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        if(!removed[i][j] && visited[i][j] && storage[i].charAt(j) == request.charAt(0)){
                            removed[i][j] = true;
                        }
                    }
                }
            }
            else{
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        if(!removed[i][j] && storage[i].charAt(j) == request.charAt(0)){
                            removed[i][j] = true;
                        }
                    }
                }
            }
            visited = bfs(visited, removed);
        }
        for(int i = 0 ; i< n; i++){
            for(int j = 0; j < m; j++){
                if(!removed[i][j]) answer++;
            }
        }
        return answer;
    }
    
    static boolean[][] bfs(boolean[][] canGo, boolean[][] removed){
        boolean[][] v = new boolean[n][m];
        for(int i = 0; i < n; i++){
            v[i] = Arrays.copyOf(canGo[i], m);
        }
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        for(int i = 0; i <n; i++){
            for(int j = 0; j < m; j++){
                if(canGo[i][j] && removed[i][j]){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];
            
            for(int d = 0; d< 4; d++){
                int ny = y + dy[d], nx = x + dx[d];
                
                if(inRange(ny,nx) && !canGo[ny][nx] && !visited[ny][nx]){
                    visited[ny][nx] = true;
                    v[ny][nx] = true;
                    if(removed[ny][nx]){
                        q.add(new int[]{ny,nx});
                    }
                }
            }
        }

        return v;
    }
    
    static boolean inRange(int y, int x){
        return -1 < y && y < n && -1 < x && x < m;
    }
}