//항상 여는 게 먼저 나와야하고 닫히는게 보이면 유효한 카운트
//돌려보면서 유효한게 몇개인지 확인하기
import java.util.*;

class Solution {
    //잘못짠 isValid -> 괄호 깔맞춤을 안해서 [)(]도 맞다고 판단
    //    private boolean isValid(String s) {
    //        int balance = 0;
    //
    //        for (char c : s.toCharArray()) {
    //            if (c == '(') {
    //                balance++;
    //            } else if (c == ')') {
    //                balance--;
    //            } else if (c == '{') {
    //                balance++;
    //            } else if (c == '}') {
    //                balance--;
    //            } else if (c == '[') {
    //                balance++;
    //            } else if (c == ']') {
    //                balance--;
    //            }
    //
    //            // 닫는 괄호로 시작하면
    //            if (balance < 0) return false;
    //        }
    //
    //        return balance == 0;
    //    }

    //깔맞춤해서 체크해야함..
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public int solution(String s) {
        int answer = 0;

        int n = s.length();

        // 각 회전 위치마다 확인
        for (int i = 0; i < n; i++) {
            String rotated = s.substring(i) + s.substring(0, i);

            if (isValid(rotated)) {
                answer++;
            }
        }

        return answer;
    }
}