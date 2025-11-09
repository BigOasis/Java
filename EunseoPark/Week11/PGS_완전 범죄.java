import java.util.*;

/*
    [참고]
        1. 맨 처음 백트레킹은 시간초과나므로 DP를 떠올렸지만 정확히 어떻게 구현할지 떠오르지 않음
        2. 백트레킹 -> 2 ^ 40 으로 시간초과
*/
class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length + 1][m];
        for(int i = 1; i <= info.length; i++){
            Arrays.fill(dp[i], n);
        }
        
        dp[0][0] = 0;
        
        for(int i = 1; i<= info.length; i++){
            int a = info[i-1][0], b = info[i-1][1];
            
            for(int j = 0; j < m; j++){
                dp[i][j] = Math.min(dp[i-1][j] + a, dp[i][j]);
                
                if(j + b < m){
                    dp[i][j+b] = Math.min(dp[i-1][j], dp[i][j+b]);
                }
            }
        }
        
        int ans = n;
        for(int i = 0; i < m; i++){
            ans =Math.min(ans, dp[info.length][i]);
        }
        return ans >= n ? -1 : ans;
    }
}