import java.util.*;
/*
    1. n개 중에서 5개뽑기 
    2. 뽑은 숫자와 q[i]의 교집합 개수가 ans[i]면 끝까지 확인 후 맞으면 ans++;
*/
class Solution {
    static int m;
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        m = q.length;
        List<Set<Integer>> result = new ArrayList<>();
        comb(1, n, new HashSet<>(), result);
        
        for(Set<Integer> r : result){
            boolean flag = true;
            
            for(int i = 0; i < m; i++){
                int count = 0;
                for(int j = 0; j < 5; j++){
                    if(r.contains(q[i][j])) count++;
                }
                if(count != ans[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;   
        }
        
        return answer;
    }
    
    static void comb(int index, int n, Set<Integer> cur, List<Set<Integer>> result){
        if(cur.size() == 5){
            result.add(new HashSet<>(cur));
            return;
        }
        for(int i = index; i <= n; i++){
            cur.add(i);
            comb(i + 1, n, cur, result);
            cur.remove(i);
        }
    }
}