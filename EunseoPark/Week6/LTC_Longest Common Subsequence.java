/*
  [스스로 풀이 : 오답]
    1. 맨 처음 dp가 무엇을 하는지는 맞았음.
    2. 그런데 len1 + 1, len2 + 1로 0인 위치를 0으로 초기화하여 사용하고
    3. 같을 때를 가장 왼,위,대각선 3개 중 max 값 + 1로 해버림 -> 이 부분이 잘못됨
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];

        for(int i = 1; i<= len1; i++){
          for(int j = 1; j<= len2; j++){
            if(text1.charAt(i-1) == text2.charAt(j-1)){
              dp[i][j] = dp[i-1][j-1] + 1;
            } 
            else{
              dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
          }
        }

        return dp[len1][len2];
    }
}