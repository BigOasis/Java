import java.util.*;

/*
  1. L: 0, R: t의 길이 -1
  2. while R < 끝 :
     (L + 1 ~ R) 의 개수 == ( L ~ R ) 개수 : L++ 
     else : R ++
  3. 개수 세기 : substring다 돌면서 문자 개수 value == t의 문자 개수 : true

  지금 개수로 안되어있음 개수세기 가 boolean으로 되어 있음, int 반환으로 바꿔서 L갱신 예정
*/

class Solution {
    public String minWindow(String s, String t) {
        int tlen = t.length();
        int slen = s.length();

        if(slen < tlen) return "";
        int L = 0, R = tlen - 1;
        int ansL = 0, ansR = slen;
        boolean find =false;

        while(R < slen){

          if(getCount(L,R,s,t)){
            find = true;
            if(ansR - ansL > R - L){
              ansL = L; ansR = R;
            }
            L++;
          }
          else{
            R++;
          }
        }
        
        if(find) return s.substring(ansL, ansR+1);
        return "";
    }
    static boolean getCount(int L, int R,String s, String t){
      Map<Character, Integer> hmt = new HashMap<>();
      Map<Character, Integer> hms = new HashMap<>();

      for(char c : t.toCharArray()){
        hmt.put(c, 
        hmt.getOrDefault(c, 0) + 1
        );
      }

      String newStr = s.substring(L,R+1);
      for(char c : newStr.toCharArray()){
        hms.put(c, hms.getOrDefault(c, 0) + 1);
      }

      for(char c : hmt.keySet()){
        if(!hms.containsKey(c) || hmt.get(c) != hms.get(c)) return false;;
      }
      return true;
    }
}