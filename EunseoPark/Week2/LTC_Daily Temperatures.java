import java.util.*;
/*
    [스스로 풀이 : 10분] 23ms, 57MB
        1. output에 들어가는 값이 더 온도가 높아지는 때까지의 인덱스 차이 -> 인덱스를 스택에 넣어야 함.
        2. while문으로 더 높은 온도 나오면 계속 pop하면서 인덱스 차이 넣기
*/
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < length; i++){
            while( !stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[i] ){
                int pop = stack.removeLast();
                result[pop] = i - pop;
            }
            stack.addLast(i);
        }
        return result;
    }
}