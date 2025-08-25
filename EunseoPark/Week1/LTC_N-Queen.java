import java.util.*;
/* 
    1. 방문 위치를 기록하기 위해 -1로 초기화하고, (행 번호, 놓인 열 위치) 기록
    2. -1 && 갈 수 있으면 backtrack
    2. canGo :
		    첫 시도 :
			    now가 0, n-1, 중간일때 상하좌우 대각선을 while로 업데이트하면 확인 
*/
class Solution {
    static int[] visited;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        visited = new int[n];
        Arrays.fill(visited, -1);
        backtrack(0, n, result); 
        return result;
    }
    private void backtrack(int now, int n, List<List<String>> result){
        if(now == n) {
            List<String> cur = new ArrayList<>();
            
            for(int idx : visited){
                StringBuilder sb = new StringBuilder();
                sb.append(".".repeat(n));
                sb.setCharAt(idx,'Q');
                cur.add(sb.toString());
            }
            result.add(cur);
            return;
        }
        for(int t = 0; t < n; t++){
            if(canGo(now, t, n)){
                visited[now] = t;
                backtrack(now + 1, n , result);
                visited[now] = -1;
            }
        }
    }

    // now 번째 queen이 t 인덱스에 갈 수 있는지
    // 🔍 이전 행만 확인
    private boolean canGo(int now, int t, int n){
        for(int pre = 0; pre < now; pre++){
            if(visited[pre]==t) return false; //이미 지난
            // 🔍 [행 차 == 열 차
            if(Math.abs(pre - now) == Math.abs(visited[pre] - t)) return false;
        }
        return true;
    }
}