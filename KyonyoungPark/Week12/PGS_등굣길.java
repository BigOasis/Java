// 아래, 오른쪽으로밖에 못감
// m*n dp 배열로 채우기
class Solution {
    final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        boolean[][] isPuddle = new boolean[n+1][m+1];

        // 웅덩이 표시
        for (int[] p : puddles) {
            isPuddle[p[1]][p[0]] = true;
        }

        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (isPuddle[i][j]) {
                    dp[i][j] = 0;
                } else if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
                }
            }
        }

        return dp[n][m];
    }
}