import java.util.*;

class Solution {
    static int N;
    static int maxDiff;
    static int[] maxScore = new int[11];
    
    public int[] solution(int n, int[] info) {
        N = n;
        maxDiff = -1;
        int[] score = new int[11];
        dfs(0,n,score,info);
        return maxDiff == -1 ? new int[]{-1} : maxScore;
    }
    
    // 현재 인덱스, 남은 화살, 현재 라이언 점수
    static void dfs(int i, int n, int[] score, int[] info){
        
        if(n < 0) return;

        // 남은 화살이 없거나 경기가 끝났다면
        if(n == 0 || i == 11) {
            if(n > 0){
                score[10] += n;
            }
            int appeach = 0, lion = 0;
            for(int j = 0; j < 11; j++){
                if(info[j] == score[j]){
                    if(info[j] > 0){
                        appeach += 10 - j;
                    }
                }
                else if(info[j] > score[j]){
                    appeach += 10 - j;
                }
                else {
                    lion += 10 - j;
                }
            }
            int diff = lion - appeach;
           
            // 여기서 diff 가 0 인 경우, 즉 비기는 경우도 라이언이 지는 것임. 따라서 갱신은 항상 diff가 0보다 클때
            // diff <= 0 일때 return 할 거면 score[10] 원상복구 잊지말고 반환해야 함
            if(diff > 0){
                if(maxDiff == diff){
                    for(int arrow = 10; arrow > -1; arrow--){
                        if(maxScore[arrow] < score[arrow]){
                            maxScore = Arrays.copyOf(score, 11);
                            break;
                        }
                        // 현재 maxScore가 더 낮은 점수에 유리하면 교체 X
                        else if(maxScore[arrow] > score[arrow]){
                            break;
                        }
                    }
                }
                else if(maxDiff < diff){
                    maxDiff = diff;
                    maxScore = Arrays.copyOf(score,11);
                }
            }
            
            if(n > 0) score[10] -= n;
            return;
        }
        
        score[i] = info[i] + 1; 
        dfs(i+1,n - score[i], score, info); 
        
        score[i] = 0;
        dfs(i+1,n,score,info); 
        
    }
}