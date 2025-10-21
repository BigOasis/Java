
/*
    [정답 참고]
    1. dp[r][c] : r일차에 c개의 job을 수행할 때의 최소 난이도 합
*/
class Solution {

  static final int INF = Integer.MAX_VALUE;
    public int minDifficulty(int[] jobDifficulty, int d) {
      int n = jobDifficulty.length;
      if ( n < d ) return -1;
      int[][] dp = new int[d+1][n+1];

      // 1. 초기화
      dp[1][1] = jobDifficulty[0];
      for(int i = 2; i <= n - d + 1; i++){
        dp[1][i] = Math.max(dp[1][i-1], jobDifficulty[i-1]);
      }

      // 2일차부터 d일차까지
      for (int r = 2; r <= d; r++) {
        for (int c = r; c <= n-d+r; c++) {
            int maxDiff = 0;
            dp[r][c] = Integer.MAX_VALUE;
            for (int k = c; k >= r; k--) {
              maxDiff = Math.max(maxDiff, jobDifficulty[k-1]);
              dp[r][c] = Math.min(dp[r][c], dp[r-1][k-1] + maxDiff);
            }
        }
      }
      return dp[d][n];
    }
}