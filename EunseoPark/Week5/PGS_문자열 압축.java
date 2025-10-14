/*
    [스스로 풀이 : 40분] 근데 1차 시도 실패 후 2차 시도
        헷갈린 부분 
            1) size만큼 쪼개고 남은 문자는 그대로 sb에 붙이기
            2) 기준 sub과 비교하고 sub2를 만들고 그 다음 기준 sub이 될때 i가 얼만큼 뛰어넘어어야 하는지
*/
class Solution {
    public int solution(String s) {
        int len = s.length();
        int half = len / 2;
        
        int answer = len;
        
        for(int size = 1; size <= half; size++){
            StringBuilder sb = new StringBuilder();
            
            int count = 1;
            for(int i = 0; i < len; i += size * (count)){
                String sub = s.substring(i, Math.min((i + size), len));
                count = 1;
                
                for(int j = i + size; j <= len - size; j+= size){
                    String sub2 = s.substring(j, j + size);
                    if(!sub.equals(sub2)) break;
                    count++;
                }
                if(count >= 2){
                    sb.append(count);
                }
                sb.append(sub);
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}