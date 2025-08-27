import java.util.*;

/*
    [스스로 풀이] 4ms 41MB
        Stack : vector를 상속받아 모든 메서드가 동기화되어 있어 단일 스레드 환경에서 동기화 오버헤드가 큼
        Deque : 동기화 X, 단일 스레드에서 더 빠름, 내부적으로 원형 배열이라 메모리 효율성 높음, 양쪽 삽입/삭제 모두 O(1)
*/
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            if(stack.isEmpty()){
                stack.add(c);
                continue;
            }
            if(stack.peekLast() == '(' && c == ')' || stack.peekLast() == '{' && c =='}' || stack.peekLast() == '[' && c ==']'){
                stack.removeLast();
                continue;
            } 
            stack.addLast(c);
        }
        return stack.isEmpty();
    }
}

/*
    [최적호 참고] 2ms 42MB
        기존 코드는 반복마다 isEmpty를 체크하고, if문에 or로 최대 peekLast를 3번이나 확인
*/
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            if(c == '('){
                stack.add(')');
            }
            else if(c == '{'){
                stack.add('}');
            }
            else if(c=='['){
                stack.add(']');
            }else{
                if(stack.isEmpty() || stack.removeLast()!=c){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}