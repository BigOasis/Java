import java.util.*;

/*
  [정답 참고]
    stack = [-1]을 넣어놓고, 이제 들어갈 cur이 '(', ')'에 따라 구분하기
    [-1을 넣어놓는 것을 어떻게 생각하지?]
      () 이렇게 있을 때 닫히는 )이 오면 길이가 2로 된다.
      처음에는 dp로 생각해서 dp[i-2] + 2이렇게 하려고 했다. 

      그런데 )의 인덱스는 1이므로 
      1일때 2가 되어야 하니까 1-(-1) 이 되어야 한다. stack의 peek으로 하려면 peek에 -1이 있어야 하는 이유   
*/
class Solution {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(-1);
        int longest = 0;

        for(int i = 0, size = s.length() ; i < size; i++){
          if(s.charAt(i) =='('){
            stack.addLast(i);
          }
          else{
            stack.removeLast();
            if(stack.isEmpty()){
              stack.addLast(i);
            }
            longest = Math.max(longest, i - stack.peekLast());
          }
        }
        return longest;
    }
}

import java.util.*;

/*
  [스스로 풀이 : 실패]
    포인터 이동하면서 이전거 + 1
*/

class Solution {
    public int longestValidParentheses(String s) {
        int size = s.length();
        if(size < 2) return 0;

        if(size == 2) {
          if(s.charAt(0) == '(' &&  s.charAt(1) ==')') return 2;
          return 0;
        }
        
        int[] count = new int[size];
        if(s.charAt(0) == '(' && s.charAt(1) == ')'){
          count[1] = 2;
        }

        for(int i = 2; i < size; i++){
          if(s.charAt(i-1) == '(' && s.charAt(i) ==')'){
            count[i] = count[i-2] + 2;
          }
        }

        System.out.println(Arrays.toString(count));
        int max = 0;
        for(int c : count){
          max = Math.max(max, c);
        }
        return max;
    }
}