import java.util.*;

/*
    [스스로 풀이 : 30분]
*/
class Solution {
    static char[] code = {'R','T','C','F','J','M','A','N'};
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> codeHm = new HashMap<>();
        for(int i = 0; i < 8; i++){
            codeHm.put(code[i], i);
        }
        
        int[][] count = new int[4][2];
        int n = survey.length;
        for(int i = 0; i< n; i++){
            int c = choices[i];
            c -= 4;
            if(c == 4) continue;
            if(c > 0){
                int idx = codeHm.get(survey[i].charAt(1));
                count[idx/2][idx%2]+= c;
            }else{
                int idx = codeHm.get(survey[i].charAt(0));
                count[idx/2][idx%2]+= c * -1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< 4; i++){
            char c = code[i*2];
            if(count[i][0] < count[i][1]) {
                c = code[i*2 + 1];
            }
            sb.append(c);
        }
        return sb.toString();
    }
}