import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String id : id_list) {
            reportMap.put(id, new HashSet<>());
        }

        for (String r : report) {
            String[] parts = r.split(" ");
            String from = parts[0];
            String to = parts[1];

            reportMap.get(from).add(to);
        }

        //각 유저가 신고당한 횟수 카운트
        Map<String, Integer> reportCount = new HashMap<>();
        for (String from : reportMap.keySet()) {
            for (String to : reportMap.get(from)) {
                reportCount.put(to, reportCount.getOrDefault(to, 0) + 1);
            }
        }

        // k번 이상만 검거
        Set<String> multireport = new HashSet<>();
        for (String user : reportCount.keySet()) {
            if (reportCount.get(user) >= k) {
                multireport.add(user);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            int mailCount = 0;

            // 이 유저가 신고한 사람들 중 정지된 사람 수
            for (String to : reportMap.get(user)) {
                if (multireport.contains(to)) {
                    mailCount++;
                }
            }

            answer[i] = mailCount;
        }

        return answer;
    }
}