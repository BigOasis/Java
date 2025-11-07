import java.util.*;

/*
    [스스로 풀이 : 40분]
        1. 각 열에서 최대 peek을 가지고 idx로 바로 접근하면 O(1) -> 각 열 최대 peek 위치 pos로 저장
        2. stack에서 peek과 동일하면 ans +=2, pop() 해서 삭제
        3. 해당 열의 꼭대기 한칸 내려가기 pos++;
*/
class Solution {
    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        int[] pos = new int[N];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[j][i] != 0){
                    pos[i] = j;
                    break;
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        
        for(int move : moves){
            move -= 1;
            int idx = pos[move];
            if(idx >= N) continue;
            int emg = board[idx][move];
            pos[move]++;
            if(!stack.isEmpty() && stack.peek() == emg){
                stack.pop();
                ans += 2;
                continue;
            }
            stack.push(emg);
        }
        
        return ans;
    }
}