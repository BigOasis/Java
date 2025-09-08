import java.util.*;

/*
    [ 1차 코드드 설계 ]
        1. orders 돌면서 combination으로 2~길이개 뽑고 오름차순
        2. 뽑고 hm에 넣기
            3. hm에 없다면 (조합, 횟수 = 1)
            4. 있다면      횟수 + 1
        5. 횟수가 2이상 인 것만 빼고, 오름차순

    [ 놓친 부분 ]
        1. "가장 많이 함께 주문한" 문구를 놓쳐 중복으로 저장됨
        2. 테케 3번 "WX WY" 로 들어감 ("WX","XY" 로 들어가야야)
    
*/
class Solution {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> hm = new HashMap<>();
        
        for(int i = 0, length = orders.length; i < length; i++){
            // orders 돌면서 2~10개씩 combination
            for(int count = 2, max = orders[i].length(); count <= max; count++){
                List<String> combs = comb(orders[i], count);
                for(String c : combs){
                    hm.put(c, hm.getOrDefault(c, 0) + 1);
                }
            }
        }
        // value 값이 2 이상이면 넣기
        List<String> ans = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : hm.entrySet()){
            if(entry.getValue() > 1) ans.add(entry.getKey());
        }
        String[] answer = new String[ans.size()];
        for(int i = 0, l = ans.size(); i < l; i++){
            answer[i] = ans.get(i);
        }
        System.out.println(ans);
        
        return answer;
    }
    
    private List<String> comb(String order, int count){
        List<String> result = new ArrayList<>();
        System.out.println("count = " + count +"---------------");
        makeComb(order, result, new ArrayList<>() , count, 0);
        System.out.println(result);
        return result;
    }
    
    private void makeComb(String order, List<String> result, List<Character> cur, int count, int index){
        if(cur.size() == count) {
            Collections.sort(cur); //생략 가능?
            StringBuilder sb = new StringBuilder();
            for(char c : cur){
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }
        
        for(int i = index, length = order.length(); i < length; i++){
            cur.add(order.charAt(i));
            makeComb(order, result, cur, count, i+1);
            cur.remove(cur.size()-1);
        }
    }
}