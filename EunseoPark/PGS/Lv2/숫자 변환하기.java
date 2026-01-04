import java.util.*;

/*
    dp로 메모이제이션을 하고, 연산이 더했다가 곱한 것으로 여러가지 방법을 사용할 수 있으니, 현재 계산한 값을 q에 다시 넣어 반복. 
    처음에는 x -> y로 출발해서 y로 도달하기까지 불필요한 값들도 q에 들어감 -> 메모리 초과
    다른 사람의 접근법 참고 -> y에서 출발해서 x까지 가는 것이 실제 밟는 값들만 q에 넣을 수 있음. -> 시간초과
    q에 연산한 모든 값을 넣다보니 시간초과 -> dp가 더 큰 상태일때만 업데이트하고 q에 넣기
*/
class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Deque<Integer> q = new ArrayDeque<>();
        q.add(y);
        dp[y]= 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();

            if((cur - n) >= x && dp[cur-n] > dp[cur] + 1){
                dp[cur - n] = dp[cur] + 1;
                q.add(cur - n);
            }
        
            for(int i = 3; i > 1; i--){
                if(cur % i == 0 && cur / i >= x){
                    if(dp[cur / i] > dp[cur] + 1){
                        dp[cur/i] = dp[cur] + 1;
                        q.add(cur/i);
                    }
                }
            }
        }
        return dp[x] == Integer.MAX_VALUE ? -1 : dp[x];
    }
}