import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        boolean isFirst = true;
        for(char c: s.toCharArray()){
            if(c == ' '){
                sb.append(c);
                isFirst = true;
            }
            else{
                if(isFirst){
                    isFirst = false;
                    if(!Character.isDigit(c)){
                        c = Character.toUpperCase(c);
                    }
                    sb.append(c);
                }else{
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}

import java.util.*;

class Solution {
    public String solution(String s) {
        // -1을 해야 마지막 공백 유지
        String[] strs = s.toLowerCase().split(" ", -1);
        
        for(int i = 0; i < strs.length; i++){
            String str = strs[i];
            
            if(!str.isEmpty()){
                // toUpper, toLower은 문자 그대로 돌려줌 (특수문자, 숫자면 그냥 그대로 숫자면 그냥 그대로)
                strs[i] = Character.toUpperCase(str.charAt(0)) + str.substring(1);
            }
        }
        
        return String.join(" ", strs);
    }
}
