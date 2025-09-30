import java.util.*;

/*
  [스스로 풀이 : 5분] 1ms 40MB
*/
class Solution {
    public int uniquePaths(int m, int n) {
      int[][] dp = new int[m][n];

      for(int i = 0; i < n; i++){
        dp[0][i] = 1;
      }        

      for(int i = 1; i < m; i++){
        dp[i][0] = 1;
      }

      for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
          dp[i][j] = dp[i-1][j] + dp [i][j-1];
        }
      }
      return dp[m-1][n-1];
    }
}