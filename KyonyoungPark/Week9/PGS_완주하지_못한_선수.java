import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> entries = new HashMap<>();
        String answer = "";
        // 명단 등록
        for(String p: participant) {
            entries.put(p, entries.getOrDefault(p, 0) + 1);
        }

        for(String c: completion) {
            entries.put(c, entries.getOrDefault(c, 0) - 1);
        }

        //완주 못하면 value가 0이 되지 않음.
        for(String p: participant) {
            int remain = entries.get(p);
            if(remain> 0){
                answer = p;
            }
        }


        return answer;
    }
}