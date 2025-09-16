import java.util.*;

/*
    1. orders 돌면서 combination으로 2~10개 뽑고 오름차순
    2. 2개 orders를 돌면서 2개 조합으로 할 수 있는애들을 set에 넣어서 중복 제거
    3. 조합 하나씩 돌면서 그게 orders에 몇번 포함된 주문인지 개수 세기 (2개 조합중 가장 많이 주문된 조합만)
*/
class Solution {
    public String[] solution(String[] orders, int[] courses) {
        
        List<String> result = new ArrayList<>();
        
        // 처음에는 2~10으로 했는데 이러면 범위를 넘어감 -> course 활용
        for(int course : courses){
            Map<String, Integer> combHm = new HashMap<>();
            
            for(String order: orders){
                List<String> combs = combination(order, course);
                for(String comb : combs){
                    if(combHm.containsKey(comb)) continue; 

                    // AC 가 떨어져있어도 포함되었는지 판단
                    int count = 0;
                    for(String order2 : orders){
                        boolean ok = true;
                        for(char c : comb.toCharArray()){
                            if(order2.indexOf(c) < 0){
                                ok = false;
                                break;
                            }
                        }
                        if(ok) count++;
                    }
                    if(count > 1) combHm.put(comb, count);
                }
            }
            
            // 해당 개수의 조합에서 제일 많이 나온 조합을 찾기
            if(combHm.isEmpty()) continue; // hm이 비어있는 상태에서 Collections.max 하면 에러남
            int maxCount = Collections.max(combHm.values());
            for(Map.Entry<String,Integer> entry : combHm.entrySet()){
                if(entry.getValue() == maxCount){
                    result.add(entry.getKey());
                }
            }
        }
                
        String[] answer = new String[result.size()];
        for(int i = 0, l = result.size(); i < l; i++){
            answer[i] = result.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    private List<String> combination (String order, int count){
        List<String> result = new ArrayList<>();
        makeComb(order, result, new ArrayList<>() , count, 0);
        return result;
    }
    
    private void makeComb(String order, List<String> result, List<Character> cur, int count, int index){
        if(cur.size() == count) {
            List<Character> copy = new ArrayList<>(cur);
            Collections.sort(copy); //생략하면 XW 와 WX를 다르게 판단하게 됨, 정렬해야 무조건 WX로 나옴
            StringBuilder sb = new StringBuilder();
            for(char c : copy){
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