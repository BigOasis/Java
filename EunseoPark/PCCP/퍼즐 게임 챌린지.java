import java.util.*;

/*
    [처음 시도 : 순차 탐색]
        while문안에서 level 올라가면서 앞에서부터 모두 계산
            times 최대 : 300,000 (매 level 마다 times를 다 돌면서 확인)
            level 최대 : 퍼즐의 난이도를 담은 diff 중 최대가 level의 최대가 된다 -> max_diff 
                        diff는 최대 100,000
            순차 탐색으로 하면 시간초과
    
    [다른 사람 풀이 참고 : 이진 탐색]
        diff를 오름차순한 뒤, 이분탐색으로 최소 level을 찾아가야 함
        현재 level로 했을 때 넘어가면 level을 높여야 함 
        
        근데 범위가 diff가 아니라 0 ~ maxDiff 로 하고 diff에 없는 값이 될 수도 있음 -> 즉, 배열에서 값을 정하면 안되고, 사이에서 움직이면서 찾아야 함
        i = 0 일때 i-1 접근하려고 하면 에러나므로 -> false 반환 (왜냐면 문제에서 항상 되는 경우라고 했으니까 i = 0일때 i-1에 접근하는 경우는 정답이 아니라는 소리)
*/
class Solution {
    
    static boolean isValid(int[] diffs, int[] times, long limit, int level){
        int n = diffs.length;
        long sum = 0;

        for(int i = 0; i < n; i++){
            if(diffs[i] <= level) {
                sum += times[i];
            }else{
                if(i == 0){
                    return false;
                }
                int miss = diffs[i] - level;
                sum += (times[i] + times[i-1]) * miss  + times[i];
            }
        }
        return sum <= limit;
        
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        int[] sortedDiff = Arrays.copyOf(diffs, n);
        Arrays.sort(sortedDiff);

        int start = 0, end = sortedDiff[n-1];
        int answer = 0;
        
        while(start <= end){
            int level = (start + end) / 2;

            if(isValid(diffs, times, limit, level)){
                answer = (int) level;
                end = level - 1;
            }
            else {
                start = level + 1;
            }
        }
        
        return answer;
    }
}