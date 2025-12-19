import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int len = sequence.length;
        
        int L =0, R = 0;
        int ansR = len, ansL = 0;
        int sum = 0;
        
        while(R < len){
            sum += sequence[R];
            
            while(sum > k){
                sum -= sequence[L++];
            }
            if(sum == k){
                if(R- L < ansR - ansL){
                    ansR = R; ansL = L;
                }
            }
            R++;
        }
        return new int[]{ansL, ansR};
    }
}