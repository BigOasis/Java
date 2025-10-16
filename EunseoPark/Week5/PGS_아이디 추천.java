import java.util.*;

/*
    [오답]
      1. c-'a' <= 25 까지여야 하는데 26으로 함
      2. ... "여러개"가 포함된 경우 .로 하는 것을 생각 못함 -> while(s.contains(".."))일때까지 replace하거나 {"\\.{2,}"} -> .이 2개 이상이면
*/
class Solution {
    public String solution(String s) {
        
        // 1. 대문자 -> 소문자
        s = s.toLowerCase(); 
        
        // 2. 문자 제거
        StringBuilder sb = new StringBuilder("");
        for(Character c : s.toCharArray()){
            if(c == '.' || c == '_' || c =='-' || ( 'a' <= c && c<= 'z' ) || ( '0' <= c && c <= '9')){
                sb.append(c);
                continue;
            }
        }
        
        // 3. ... .. -> .
        s = sb.toString().replaceAll("\\.{2,}", "."); //2개 이상 연속된 . -> . 1개로 치환 (.만 있으면 모든 문자를 뜻하므로 이스케이프)
        
        // 4. 처음, 마지막 위치 . 제거
        if(s.startsWith(".")){
            s = s.substring(1, s.length());
        }
        if(s.length() != 0 && s.endsWith(".")){
            s = s.substring(0, s.length() - 1);
        }
        
        // 5. 빈 문자열이면 a 대입
        if(s.length() == 0){
            return "aaa";
        } 
        
        // 6. 16자 이상 -> (0,15)개까지
        // 이후 마지막이 .이면 마지막 . 제거
        if(s.length() >= 16){
            s = s.substring(0,15);
            if(s.endsWith(".")){
                s = s.substring(0,14);
            }
        }
        
        // 7. 길이 <=2 -> 3이 될때까지 반복
        int len = s.length();
        sb = new StringBuilder(s);
        if(len < 3) {
            char last = s.charAt(s.length()-1);
            int count = 3 - len;
            for(int i = 0; i < count; i++){
                sb.append(last);
            }
        }
        
        return sb.toString();
    }
}