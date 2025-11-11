class Solution {
    public int solution(int[][] triangle) {
        // n * n개의 배열 만들어서 최대값 메모이제이션
        int n = triangle.length;
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n-1][j] = triangle[n-1][j];
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] +
                        Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        int answer = dp[0][0];
        return answer;
    }
}