//1차 시도: visited 배열 기반 그리디 시도
//멸망 - 최소값을 구하는 방법이 될 수 없음

//2차 시도: dp

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 가장 요구치 높은 알골력과 코딩력을 각각 구하기
        int targetAlp = 0;
        int targetCop = 0;

        for (int[] p : problems) {
            targetAlp = Math.max(targetAlp, p[0]);
            targetCop = Math.max(targetCop, p[1]);
        }

        // 이미 목표를 달성한 경우
        if (alp >= targetAlp && cop >= targetCop) {
            return 0;
        }

        // 시작점이 목표보다 크면 목표로 조정
        alp = Math.min(alp, targetAlp);
        cop = Math.min(cop, targetCop);

        // dp 사용 : alp가 i, cop가 j인 상태에 도달하는 최소 시간을 2차원배열로 관리
        int[][] dp = new int[targetAlp + 1][targetCop + 1];

        for (int i = 0; i <= targetAlp; i++) {
            for (int j = 0; j <= targetCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= targetAlp; i++) {
            for (int j = cop; j <= targetCop; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;

                //1시간에 알고력/코딩력 1개씩 버는 거로 일단 채우기(문제 풀어서 얻는 방식이 더 효율적이면 덮어씌워짐)
                if (i + 1 <= targetAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= targetCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                //문제로 갱신하기
                for (int[] p : problems) {
                    int alp_req = p[0];  // 요구 알고리즘
                    int cop_req = p[1];  // 요구 코딩
                    int alp_rwd = p[2];  // 알고리즘 보상
                    int cop_rwd = p[3];  // 코딩 보상
                    int cost = p[4];     // 시간

                    // 풀 수 있을 때만 테스트
                    if (i >= alp_req && j >= cop_req) {
                        int newAlp = Math.min(i + alp_rwd, targetAlp);
                        int newCop = Math.min(j + cop_rwd, targetCop);

                        dp[newAlp][newCop] = Math.min(
                                dp[newAlp][newCop],
                                dp[i][j] + cost
                        );
                    }
                }
            }
        }

        return dp[targetAlp][targetCop];
    }
}