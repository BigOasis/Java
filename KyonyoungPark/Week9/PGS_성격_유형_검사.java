import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        // 성격 유형별 점수를 저장할 맵
        Map<Character, Integer> scores = new HashMap<>();
        scores.put('R', 0);
        scores.put('T', 0);
        scores.put('C', 0);
        scores.put('F', 0);
        scores.put('J', 0);
        scores.put('M', 0);
        scores.put('A', 0);
        scores.put('N', 0);

        for (int i = 0; i < survey.length; i++) {
            char type1 = survey[i].charAt(0);  // 비동의
            char type2 = survey[i].charAt(1);  // 동의
            int choice = choices[i];

            int score = 0;


            //4면 모르겠음
            if (choice == 1 || choice == 7) {
                score = 3;  // 매우
            } else if (choice == 2 || choice == 6) {
                score = 2;  // 일반
            } else if (choice == 3 || choice == 5) {
                score = 1;  // 약간
            }

            if (choice < 4) {
                // 비동의 쪽 선택
                scores.put(type1, scores.get(type1) + score);
            } else if (choice > 4) {
                // 동의 쪽 선택
                scores.put(type2, scores.get(type2) + score);
            }
        }


        //알파벳 순으로 붙이기
        StringBuilder result = new StringBuilder();
        result.append(scores.get('R') >= scores.get('T') ? 'R' : 'T');
        result.append(scores.get('C') >= scores.get('F') ? 'C' : 'F');
        result.append(scores.get('J') >= scores.get('M') ? 'J' : 'M');
        result.append(scores.get('A') >= scores.get('N') ? 'A' : 'N');

        return result.toString();
    }
}