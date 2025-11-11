// 정답 참고
// 1~8자리 N으로 만드는 수를 먼저 만들어놓고,
// 더 짧은 수들을 조합해서 (최대 8) 나오면 카운트 반환

import java.util.*;

class Solution {
    int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];  // 최대 8개까지만 사용

        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();

            int num = 0;
            for (int j = 0; j < i; j++) {
                num = num * 10 + N;
            }
            dp[i].add(num);

            //더 작은 개수들로 만든 수들을 조합
            for (int j = 1; j < i; j++) {
                for (int a : dp[j]) {
                    for (int b : dp[i-j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                    }
                }
            }

            // 목표 숫자를 찾으면 반환
            if (dp[i].contains(number)) {
                return i;
            }
        }

        return -1;
    }
}