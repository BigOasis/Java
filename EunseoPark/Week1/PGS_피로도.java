import java.util.*;
/*
  visited를 전역변수 vs 함수 인자로 넘길까 ?   => 함수 인자로 넘기는 것이 더 효율적
    1. 함수 인자로 넘겨준다고 해서 현재 visited는 배열이므로 배열의 참조값만 넘김, 값을 복사 x (성능 영향 X) 
    
    전역변수로 하면 코드가 깔끔하지만, Solutions 함수를 호출할 때마다 잔존 값의 영향 받을 수 있음.
    함수 인자로 넘기면 배열의 참조값만 넘기므로 성능 영향 X -> 함수 인자로 넘기는 것이 더 빨랐음
*/
class Solution {
    
    int result;
    
    public int solution(int k, int[][] dungeons) {
        result = 0;
        int length = dungeons.length;
        boolean[] visited = new boolean[length];
        
        dfs(k, dungeons, length, visited, 0);
        return result;
    }
    
    public void dfs(int power, int[][] dungeons, int length, boolean[] visited, int count){
        if(result == length) return;
        result = Math.max(result, count);
        
        for(int i = 0; i < length; i++){
            // 방문 x , 남아있는 힘 >= 최소 피로도
            if(!visited[i] && power >= dungeons[i][0]){
                visited[i] = true;
                dfs(power-dungeons[i][1], dungeons, length, visited, count+1);
                visited[i] = false;
            }
        }
    }
}