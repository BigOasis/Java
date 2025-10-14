import java.util.*;

/*
    [배운 점]
        split(".") 에서 .은 정규식에서 모든 것을 의미하므로 이스케이프("\\.")

    [스스로 풀이 : 40분?]
        1. 시간처럼 날짜를 day로 다 변환하기
        2. hm으로 { 약관 종류, 유효기간 }
        3. {인덱스, day로 변환(수집일자 + 유효기간)}
*/

class Solution {
    static final int YEAR = 336;
    static Map<String, Integer> termHm;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        termHm = new HashMap<>();
        for(String term : terms){
            String[] data = term.split(" ");
            termHm.put(data[0], Integer.parseInt(data[1]));
        }
        
        Map<Integer, Integer> priHm = new HashMap<>();
        int i = 1;
        for(String privacy : privacies){
            String[] data = privacy.split(" ");
            int d = changeToDate(data[0]);
            int term = termHm.get(data[1]);
            d += term * 28;
            priHm.put(i++, d-1);
        }
        
        List<Integer> list = new ArrayList<>();
        int todayToDate = changeToDate(today);
        
        for(int key : priHm.keySet()){
            if(priHm.get(key) < todayToDate){
                list.add(key);
            }
        }
        int[] ans = new int[list.size()];
        for(int j = 0; j < list.size(); j++){
            ans[j] = list.get(j);
        }
        return ans;
    }
    static int changeToDate(String data){
        String[] date = data.split("\\.");
        int d = Integer.parseInt(date[0]) * YEAR + Integer.parseInt(date[1]) * 28 + Integer.parseInt(date[2]);
        return d;
    }
}