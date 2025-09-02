import java.util.*;

/*
    [스스로 풀이: 30분] 11ms 44MB
        dp 초기값을 Integer.MAX_VALUE로 했는데 MAX_VALUE + 1 오버플로우 나서 -21~~~7 로 마이너스 값으로 바뀜
        그래서 그냥 적당히 큰 값 999_999로 함

        [999_999로 한 이유 : 최적화 참고]
            amount가 최대 10^4인데, coin이 1일때 dp[amount] 의 최대가 10^4임, 따라서 10^4 + 1 로 해도 됨.
            -> 10^4 + 1로 바꿈

*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        
        // i원을 만들 수 있는 동전의 최소 개수
        int[] dp = new int[amount + 1];
        // Arrays.fill(dp, 999_999);
        Arrays.fill(dp, 10001);
        dp[0] = 0;

        for(int coin = 0, length = coins.length; coin < length; coin++){
            for(int i = coins[coin]; i <= amount; i++){
                dp[i] = Math.min(dp[i], dp[i - coins[coin]] + 1);
            }
        }

        return dp[amount]== 10001 ? -1 : dp[amount];
    }
}