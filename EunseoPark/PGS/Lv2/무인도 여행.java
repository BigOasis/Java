import java.util.*;

class Solution {
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    static int N,M;
    
    public int[] solution(String[] maps) {
        N = maps.length; M = maps[0].length();
        boolean[][] visited = new boolean[N][M];
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i <N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j) == 'X' || visited[i][j]) continue;
                
                int count = dfs(i,j,visited,maps);
                ans.add(count);
            }
        }
        if(ans.size() == 0) return new int[]{-1};
        
        int[] result = new int[ans.size()];
        for(int i =0; i < ans.size(); i++){
            result[i] = ans.get(i);
        }
        Arrays.sort(result);
        return result;
    }
    
    static int dfs(int y, int x, boolean[][] visited, String[] maps){
        int count = maps[y].charAt(x)-'0';
        visited[y][x] = true;
        
        for(int d = 0; d < 4; d++){
            int ny = y + dy[d], nx = x + dx[d];
            if(!inRange(ny,nx) || maps[ny].charAt(nx) == 'X' || visited[ny][nx]) continue;
             count += dfs(ny,nx,visited,maps);
        }
        return count;
    }
    
    static boolean inRange(int y,int x){
        return -1 < y && y < N && -1 < x && x < M;
    }
}