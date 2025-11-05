//dfs + dp (분명 메모지에이션 없으면 시간 오링남)
import java.util.*;

class Solution {
    static int max = Integer.MAX_VALUE;

    private int dfs(int idx, int a, int b, int[][] info, int n, int m, int[][][] dp){
        if(idx == info.length){
            if(a<n && b<m){
                return a;
            }
            return max;
        }

        if(a>=n | b>=m){
            return max;
        }

        if(dp[idx][a][b] != -1){
            return dp[idx][a][b];
        }

        int res = max;

        int newA = a + info[idx][0];
        if(newA < n){
            int res1 = dfs(idx+1, newA, b, info, n, m, dp);
            res = Math.min(res, res1);
        }
        int newB = b + info[idx][1];
        if(newB < m){
            int res2 = dfs(idx+1, a, newB, info, n, m, dp);
            res = Math.min(res, res2);
        }

        dp[idx][a][b] = res;
        return res;
    }

    public int solution(int[][] info, int n, int m) {
        int answer = 0;

        int[][][] dp = new int[info.length+1][n][m];

        //디폴트 = 불가능
        for(int i = 0; i <= info.length; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        answer = dfs(0, 0, 0, info, n, m, dp);
        if(answer == max) return -1;
        else return answer;
    }
}