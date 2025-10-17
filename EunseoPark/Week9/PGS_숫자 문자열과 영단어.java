
/*
    [다른 풀이 참고]
    N : 문자열 s의 길이
        속하면 인덱스로 변경, 10회 전체 스캔 O(10 x N)
*/
class Solution {
    static String[] code = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight","nine"};
    public int solution(String s) {
        for(int i = 0; i < 10; i++){
            s= s.replace(code[i], Integer.toString(i)); //숫자를 String으로
        }
        return Integer.parseInt(s);
    }
}


import java.util.*;     

/*
    [스스로 풀이] 투 포인터 풀이 O(N)
        "1234" -> 1234 일때는 Integer.parseInt("1234")
*/
class Solution {
    static String[] code = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight","nine"};
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        Map<String,Integer> codeHm = new HashMap<>();
        for(int i =0; i< 10; i++){
            codeHm.put(code[i],i);
        }
        int L = 0, R = 0;
        int len = s.length();
        
        while(R< len){
            String sub = s.substring(L,R + 1);  
            if(L==R && !('a' <= sub.toCharArray()[0] && sub.toCharArray()[0] <= 'z')){
                sb.append(sub);
                R++;
                L = R;
                continue;  
            }
            if(codeHm.containsKey(sub)){
                sb.append(codeHm.get(sub));
                R++;
                L = R;
            }else{
                R++;
            }      
        }
        
        return Integer.parseInt(sb.toString());
    }
}