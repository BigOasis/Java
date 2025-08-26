import java.util.*;

// 최대 Count만 answer에 넣는 것까지 구현
// 알파벳 수집 및 Combination 생성 파트 수정 필요
class Solution {
    public static boolean isSubsequence(String item, String order) {
        int itemIdx = 0;
        int orderIdx = 0;

        while(itemIdx < item.length() && orderIdx < order.length()) {
            if(item.charAt(itemIdx) == order.charAt(orderIdx)) {
                itemIdx++;
            }
            orderIdx++;
        }
        return itemIdx == item.length();
    }
    private void combination(List<String> result, int[] combination, int depth, int start, int count, int range) {
        if(depth == count) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< count; i++) {
                sb.append((char)('A' + combination[i]));
            }
            result.add(sb.toString());
            return;
        }

        for(int i = start; i < range; i++) {
            combination[depth] = i;
            combination(result, combination, depth + 1, i + 1, count, range);
        }
    }
    public List<String> generateCourse(int count, int range){
        List<String> result = new ArrayList<>();
        int[] combination = new int[count];

        combination(result, combination, 0, 0, count, range);
        return result;
    }

    public List<String> solution(String[] orders, int[] course) {
        int maxLength = 0;
        int cnt = 0;
        List<Character> alpha =  new ArrayList<Character>();
        for(String s: orders) {
            maxLength = Math.max(s.length(), maxLength);
        }
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
            List<String> candidate = generateCourse(num, maxLength);
            Map<String, Integer> countMap = new HashMap<>();
            int maxCount = 0;

            for(String cand : candidate) {
                cnt = 0;
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
