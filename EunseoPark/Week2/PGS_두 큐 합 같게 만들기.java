import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        
        long q1sum = 0;
        long q2sum = 0;
        
        for(int q : queue1){
            q1.addLast(q);
            q1sum +=q;
        }
        for(int q : queue2){
            q2.addLast(q);
            q2sum += q;
        }
        
        long target = (q1sum + q2sum) / 2;
        
        // 원래 자리로 돌아올 때까지가 4번 
        for(int i = 0, n = queue1.length * 4; i < n; i++){
            if(target < q1sum){
                int pop = q1.removeFirst();
                q2.addLast(pop);
                q1sum -= pop;
                q2sum += pop;
                
            }else if(target < q2sum){
                int pop = q2.removeFirst();
                q1.addLast(pop);
                q2sum -= pop;
                q1sum += pop;
            }else{
                return i;
            }
        }
        
        return -1;
    }
}