import java.util.*;

/*
  [스스로 풀이 : 15분] 0ms 43MB
*/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = Math.min(cost[1], cost[1] + dp[0]);

        for(int i = 2; i <len; i++){
          dp[i] = Math.min(dp[i-2] , dp[i-1]) + cost[i];
        }
        return dp[len-1] > dp[len - 2] ? dp[len-2] : dp[len - 1];
    }
}