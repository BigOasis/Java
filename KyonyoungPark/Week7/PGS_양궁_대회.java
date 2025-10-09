import java.util.*;

//완탐이면 터질줄 알았는데, 이게 개수^11을 할게 아니라 어피치보다 많이넣기만하면되서
//그냥 된다/안된다 ^ 11개만 검사하면되서 완탐을 하는 게 맞음..

class Solution {
    private int maxDiff = -1;
    private int[] answer = {-1};  // 노답일때

    // 점수차 계산 (라이언 점수 - 어피치 점수)
    private int calcScore(int[] apeach, int[] ryan) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;  // 10점부터 0점까지

            if (apeach[i] == 0 && ryan[i] == 0) {
                // 둘 다 0발이면 점수 없음
                continue;
            }

            if (ryan[i] > apeach[i]) {
                ryanScore += score;
            } else {
                apeachScore += score;
            }
        }
        return ryanScore - apeachScore;
    }

    // 낮은 점수를 더 많이 맞혔는지 비교
    private boolean isLower(int[] current, int[] best) {
        for (int i = 10; i >= 0; i--) {
            if (current[i] > best[i]) {
                return true;  // current가 더 좋음
            }
            if (current[i] < best[i]) {
                return false;  // best가 더 좋음
            }
        }
        return false;  // 완전히 같음
    }

    // dfs로 10점-> 0점까지 탐색
    private void DFS(int idx, int remain, int[] apeach, int[] ryan) {
        //막타(0점)
        if (idx == 11) {
            if (remain > 0) {
                ryan[10] = remain;
            }
            int diff = calcScore(apeach, ryan);

            // 라이언이 이기는 경우만 고려
            if (diff > 0) {
                if (diff > maxDiff) {
                    // 1. 점수 갭이 더 큰 지 우선 검사
                    maxDiff = diff;
                    answer = ryan.clone();
                } else if (diff == maxDiff) {
                    // 2. 낮은걸로 더 효율적으로 먹은 지 검사
                    if (isLower(ryan, answer)) {
                        answer = ryan.clone();
                    }
                }
            }

            // 0점에 쏜 화살 복구
            if (remain > 0) {
                ryan[10] = 0;
            }
            return;
        }

        // 해당 score를 먹을 수 있으면
        int goal = apeach[idx] + 1;
        if (remain >= goal) {
            ryan[idx] = goal;
            DFS(idx + 1, remain - goal, apeach, ryan);
            ryan[idx] = 0;  // 백트래킹
        }

        // 못 먹으면 바로 패스
        DFS(idx + 1, remain, apeach, ryan);
    }

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];  // 라이언 점수 기록
        DFS(0, n, info, ryan);
        return answer;
    }
}