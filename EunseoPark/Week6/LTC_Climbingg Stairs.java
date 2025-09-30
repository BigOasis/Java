import java.util.*;

/*
   [스스로 풀이 : 5분] 1ms 40MB
*/
class Solution {
    public int climbStairs(int n) {
      int[] dp = new int[n+1];
      dp[0] = 1;
      dp[1] = 1;

      for(int i = 2; i <= n; i++){
        dp[i] = dp[i-2]+dp[i-1];
      }        
      return dp[n];
    }
}