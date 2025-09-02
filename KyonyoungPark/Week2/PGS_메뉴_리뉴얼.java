import java.util.*;

// 최대 Count만 answer에 넣는 것까지 구현
// 알파벳 수집 및 Combination 생성 파트 수정 필요
class Solution {
    // 조합이라 알파벳의 순서가 상관없었음;
    public static boolean isSubsequence(String item, String order) {
        for(char c : item.toCharArray()) {
            if(order.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
    // 지정된 List<> 알파벳 들로만 조합만들게 변경....
    private void combination(List<String> result, int[] combination, int depth, int start, int count, List<Character> alpha) {
        if(depth == count) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< count; i++) {
                sb.append(alpha.get(combination[i]));
            }
            result.add(sb.toString());
            return;
        }

        for(int i = start; i < alpha.size(); i++) {
            combination[depth] = i;
            combination(result, combination, depth + 1, i + 1, count, alpha);
        }
    }
    public List<String> generateCourse(int count, List<Character> alpha){
        List<String> result = new ArrayList<>();
        int[] combination = new int[count];

        combination(result, combination, 0, 0, count, alpha);
        return result;
    }

    public List<String> solution(String[] orders, int[] course) {
        Set<Character> alphabetSet = new HashSet<>();
        for(String order: orders){
            for(char c : order.toCharArray()) {
                alphabetSet.add(c);
            }
        }

        List<Character> alpha =  new ArrayList<Character>(alphabetSet);
        Collections.sort(alpha);

        List<String> answer = new ArrayList<String>();
//        for(int num: course){
//            List<String> candidate = generateCourse(num, maxLength);
//            for(String cand : candidate) {
//                cnt = 0;
//                for(String order : orders) {
//                    if(isSubsequence(cand, order)) {
//                        cnt++;
//                    }
//                }
//                if(cnt >= 2) {
//                    answer.add(cand);
//                }
//            }
//        }

        for(int num: course){
            // 코스에 총 알파벳 수보다 작은 수만 온다는 말이 없음..
            if(num > alpha.size()) continue;

            List<String> candidate = generateCourse(num, alpha);
            Map<String, Integer> countMap = new HashMap<>();
            int maxCount = 0;

            for(String cand : candidate) {
                int cnt = 0;
                for(String order : orders) {
                    if(isSubsequence(cand, order)) {
                        cnt++;
                    }
                }
                if(cnt >= 2) {
                    countMap.put(cand, cnt);
                    maxCount = Math.max(maxCount, cnt);
                }
            }

            // 최대 등장 횟수인 것들만 answer에 추가
            for(String cand : countMap.keySet()) {
                if(countMap.get(cand) == maxCount) {
                    answer.add(cand);
                }
            }
        }


        Collections.sort(answer);
        return answer;
    }
}
