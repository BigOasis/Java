import java.util.*;

/*
  [스스로 풀이 : 15분] 7ms 45MB
    1. 중복되지 않아야 -> set에 넣기
    2. 오른쪽으로 이동하면서 새롭게 들어올 애가 없으면 넣고,
        이미 있으면 중복되지 않은 '연속된' 문자열이므로 맨 왼쪽에 있는 문자들을 빼기 -> 투포인터 L,R 이동
          
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len < 2) return len;

        Set<Character> set = new HashSet<>();
        int answer = 0;
        int L = 0, R = 0;
        while(R < len){
          if(!set.contains(s.charAt(R))){
            set.add(s.charAt(R++));
          }else{
            set.remove(s.charAt(L++));
          }
          answer = Math.max(answer, set.size());
        }
        return answer;
    }
}