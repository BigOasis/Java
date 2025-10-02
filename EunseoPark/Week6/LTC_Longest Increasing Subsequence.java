import java.util.*;


/*
  [스스로 풀이] 41ms 44MB
    완전 탐색으로는 10^8이지만 완전히 N^2이 아니므로 이중 for문 가능하다 판단
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len == 1) return 1;

        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int answer = 1;

        for(int i = 1; i < len; i++){
          for(int j = 0; j < i; j++){
            if(nums[j] >= nums[i]) continue;
            dp[i] = Math.max(dp[j] + 1, dp[i]);
          }
          answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}