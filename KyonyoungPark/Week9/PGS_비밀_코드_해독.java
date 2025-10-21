// 총 경우의 수 자체가 30 Combination 5 * (검사에 5번 소요)로 1000000 언저리라 DFS를 통한 완탐이 가능

class Solution {
    private void DFS(int start, int depth, int n, int[][] q, int[] ans, int[] current, int[] count) {
        if (depth == 5) {
            if (isValid(q, ans, current)) {
                count[0]++;
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            current[depth] = i;
            DFS(i + 1, depth + 1, n, q, ans, current, count);
        }
    }

    private boolean isValid(int[][] q, int[] ans, int[] current) {
        for (int i = 0; i < q.length; i++) {
            int matched = 0;
            for (int q_val : q[i]) {
                for (int c_val : current) {
                    if (q_val == c_val) {
                        matched++;
                        break;
                    }
                }
            }
            if (matched != ans[i]) {
                return false;
            }
        }
        return true;
    }

    public int solution(int n, int[][] q, int[] ans) {
        int[] count = {0};
        int[] current = new int[5];

        DFS(1, 0, n, q, ans, current, count);

        return count[0];
    }
}
