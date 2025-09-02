// 두 큐의 길이는 어짜피 n으로 똑같음
// 큐 두 개를 합쳐놔서 순환 큐 모양으로 써먹기

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        for(int num: queue1)
            sum1 += num;
        for(int num: queue2)
            sum2 += num;

        long totalsum = sum1 + sum2;
        if(totalsum % 2 == 1) return -1;

        long target = totalsum / 2;

        int n = queue1.length;

        int[] circular = new int[2*n];

        for(int i = 0; i < n; i++){
            circular[i] = queue1[i];
            circular[n+i] = queue2[i];
        }

        int left = 0; int right = n; long cur = sum1; int cnt = 0;

        while(cnt <= 3* n){
            if(cur == target){
                return cnt;
            }

            if(cur > target) {
                cur -= circular[left % (2*n)];
                left++;
            } else {
                cur += circular[right % (2*n)];
                right++;
            }
            cnt++;
        }

        return -1;
    }
}