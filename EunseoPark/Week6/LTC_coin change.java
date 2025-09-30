import java.util.*;
/*
  [정답 참고] 11ms 44MB
    dp[0] = 0을 추가하니 min할 때 계속 999_999로 안 하게 됨
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
      if(amount == 0) return 0;
      int[] dp = new int[amount+1];
      Arrays.sort(coins);
      Arrays.fill(dp,999_999);
      dp[0] = 0;

      for(int coin : coins){
        for(int i = coin; i <= amount; i++){
          dp[i] = Math.min(dp[i], dp[i-coin] + 1);
        }
      }
      return dp[amount] == 999_999 ? -1 : dp[amount];
    }
}


import java.util.*;
/*
  [스스로 풀이] 16ms 44MB
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
      if(amount == 0) return 0;
      int[] dp = new int[amount+1];
      Arrays.sort(coins);
      Arrays.fill(dp,999_999);

      for(int coin : coins){
        for(int i = coin; i <= amount; i++){
          if(i%coin == 0){
            dp[i] = i/coin;
          }
          dp[i] = Math.min(dp[i], dp[i-coin] + 1);
        }
      }
      return dp[amount] == 999_999 ? -1 : dp[amount];
    }
}