import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp, maxCop = cop;
        
        for(int[] problem : problems){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        if(alp >= maxAlp && cop >= maxCop) return 0;
        
        int[][] dp = new int[152][152]; // 알고력 i, 알고력 j일때 최단 기간
        
        for(int i = alp; i <= maxAlp; i++){
            for(int j = cop; j <= maxCop; j++){
                dp[i][j] = INF;
            }
        }
        dp[alp][cop] = 0;
        
        for(int i = alp; i<= maxAlp; i++){
            for(int j = cop; j<= maxCop; j++){
                // 알고력 + 1
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                
                // 코딩력 + 1
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                
                // 문제 탐색
                for(int[] problem : problems){
                    
                    // 필요 알고력, 코딩력 만족 시
                    if(i >= problem[0] && j >= problem[1]) {
                        int nextA = Math.min(maxAlp, i + problem[2]); //목표 알고력, 누적 알고력
                        int nextC = Math.min(maxCop, j + problem[3]); //목표 코딩력, 누적 코딩력

                        dp[nextA][nextC] = Math.min(dp[nextA][nextC], dp[i][j] + problem[4]); 
                    }

                }
            }
        }
        
        return dp[maxAlp][maxCop];

    }
}