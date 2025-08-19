import java.util.*;

class Solution {
    
    int result;

    public int solution(int k, int[][] dungeons) {
        int result = 0;
        int length = dungeons.length;
        boolean[] visited = new boolean[length];
        
        return dfs(k, dungeons, length, visited, 0);
    }
    
    public int dfs(int power, int[][] dungeons, int length, boolean[] visited, int count){
        if(result == length) return result;
        result = Math.max(result, count);
        
        for(int i = 0; i < length; i++){
            // 방문 x , 남아있는 힘 >= 최소 피로도
            if(!visited[i] && power >= dungeons[i][0]){
                visited[i] = true;
                dfs(power-dungeons[i][1], dungeons, length, visited, count+1);
                visited[i] = false;
            }
        }
        return result;
    }
}