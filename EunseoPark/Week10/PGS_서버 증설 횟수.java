import java.util.*;

/*
    [스스로 풀이 30분]
        가장 먼저 든 생각은 서버를 다 가지고 매 타임마다 -1씩 해서 타이머처럼 할까 했는데 그냥 +k시간이 지날 때 -1을 함 (카카오 광고삽입 문제와 유사)

        1. 누적으로 현재 서버 개수 업데이트
        2. 더 필요하면 증설한 후, 증설한 시간 + k 위치의 증설 개수 빼주기
        
*/
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0, server = 0;
 
        int[] dp = new int[25]; //현재 서버의 개수
        for(int i = 1; i < 25; i++){
            server = dp[i] + dp[i - 1];
            int need = (int) players[i - 1] / m; //필요한 서버
            if(need > server){
                int plus = need - server;
                answer += plus;
                if((i+k) < 25){
                    dp[i+k] -= plus;                    
                }
                server += plus;                
            }
            dp[i] = server;
        }
        return answer;
    }
}