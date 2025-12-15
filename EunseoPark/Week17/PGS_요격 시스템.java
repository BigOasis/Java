import java.util.*;

/*
    끝나기 전에 미사일을 날려야 하므로, 끝나는 시간을 기준으로 오름차순
    
    현재 끝나는 시간 <= 그 다음 시작하는 시간 -> 현재 자원을 끝내야 하므로 answer ++
    i를 j로 업데이트
*/
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a,b) -> a[1]-b[1]);
        
        int i = 0, j = i + 1;
        
        while(j < targets.length){
            if(targets[i][1] <= targets[j][0]){
                answer++;
                i =j;
                j = i + 1;
            }else{
                j++;
            }
        }
        return answer + 1;
    }
}