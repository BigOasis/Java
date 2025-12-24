import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] result = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && numbers[stack.peekLast()] < numbers[i]){
                result[stack.removeLast()] = numbers[i];
            }
            stack.addLast(i);
        }
        while(!stack.isEmpty()){
            result[stack.removeLast()] = -1;
        }
        return result;
    }
}