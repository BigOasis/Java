class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 100000개의 집에 1개씩밖에 못들고다니는데 배달/수거가 둘다 많으면 int 범위넘어감
        long answer = 0;

        int deliver = 0;  // 배달해야 할 남은 개수
        int pickup = 0;    // 수거해야 할 남은 개수

        // 먼 집부터
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];

            // 할일이 있는데 여유로우면
            while (deliver > 0 || pickup > 0) {
                // 수거/배달
                deliver -= cap;
                pickup -= cap;

                // i+1번 집까지 왔다 + 갔다 해야함.
                answer += (long)(i + 1) * 2;
            }
        }

        return answer;
    }
}