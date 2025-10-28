import java.util.*;

/*
  [스스로 풀이] 533ms 46MB
		투포인터로 O(M+N)
		중복도 가능하다고 했으니까, 해시맵으로 개수도 비교
		1. thm {알파벳 : 개수}
		2. s의 L ~ R 범위 내의 thm 비교 
    => 이 코드는 매번 thm 전체를 순회해야 함.
*/
class Solution {
    public String minWindow(String s, String t) {
      int slen = s.length();
      int tlen = t.length();
      if(slen < tlen) return "";
      int maxLen = slen;
      String minStr = "";

      Map<Character, Integer> thm = new HashMap<>();
      for(char c : t.toCharArray()){
        thm.put(c, thm.getOrDefault(c, 0) + 1);
      }
      Map<Character, Integer> shm = new HashMap<>();
      int L = 0, R = tlen - 1;
      String sub = s.substring(L, R + 1);
      for(char c : sub.toCharArray()){
        shm.put(c, shm.getOrDefault(c, 0) + 1);
      }

      while(R < slen && L <= R){
        boolean hasAll = true;
        int count = 0;
        
        for(char key : thm.keySet()){
          if(shm.containsKey(key)){
            count++;
            if(shm.get(key) < thm.get(key)) hasAll = false;
          }
        }
        if(count == 0){
          R++;
          if(R < slen){
            shm.put(s.charAt(R),shm.getOrDefault(s.charAt(R),0) + 1);   
          }
          continue;
        }
        // 모두 있고,
        if(count == thm.size()){
          if(hasAll){
            char c = s.charAt(L);
            shm.put(c, shm.get(c)-1);
            if(shm.get(c).intValue() == 0){
              shm.remove(c);
            }
            if(maxLen >= (R - L + 1)){
              maxLen = R - L + 1;
              minStr = s.substring(L, R + 1);
            }
            L++;
          }else{
            R++;
            if(R < slen){
              shm.put(s.charAt(R),shm.getOrDefault(s.charAt(R),0) + 1);   
            }
          }
        }
        else{
          R++;
          if(R < slen){
            shm.put(s.charAt(R),shm.getOrDefault(s.charAt(R),0) + 1);   
          }
        }
      }

      return minStr;       
    }
}