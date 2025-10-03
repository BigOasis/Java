
/*
  [스스로 풀이 : 20분] 5ms 60MB
    1. 처음에는 -'0'으로 하려고 했는데 0으로만 되어있는 배열이 있으므로 ans 초기값을 0으로 하는 경우, 1로 하는 경우 나뉨.
      -> 매번 - dp 초기화하면서 1 있으면 ans = 1로 초기화
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      int[][] dp = new int[m][n];
      int ans = 0;

      for(int i = 0; i<m; i++){
        for(int j = 0 ; j < n; j++){
          if(matrix[i][j] == '1'){
            dp[i][j] = matrix[i][j] - '0';
            ans = 1;
          }
        }
      }

      for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
          if(matrix[i][j] == '0') continue;
          dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
          ans = Math.max(ans, dp[i][j]);
        }
      }
      return ans * ans;
    }
}