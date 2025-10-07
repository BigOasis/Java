import java.util.*;
/*
    [스스로 풀이 : 15분]
        1.{ 신고 당한 유저 : 신고한 유저 } -> 이때 인덱스로 처리해서
        2. value >=k 인 key의 value 를 돌면서 인덱스 ++ 
        3. 이를 위해 맨 처음 {id : index} 해시맵으로 해서 유저이름으로 인덱스 바로 찾기
*/
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int len = id_list.length;
        int[] answer = new int[len];
        
        Map<String, Integer> hmId = new HashMap<>();
        for(int i = 0; i < len; i++){
            hmId.put(id_list[i],i);
        }
        
        Map<Integer,Set<Integer>> hmReport = new HashMap<>();
        
        for(String r : report){
            String[] data = r.split(" ");
            int from = hmId.get(data[0]), to = hmId.get(data[1]);
            hmReport.putIfAbsent(to, new HashSet<>());
            hmReport.get(to).add(from);
        }
        
        for(Map.Entry<Integer, Set<Integer>> entry : hmReport.entrySet()){
            Set<Integer> values = entry.getValue();
            if(values.size() >= k){
                for(int value : values){
                    answer[value]++;
                }
            }    
        }
        
        return answer;
    }
}