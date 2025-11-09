import java.util.*;

/*
    [스스로 풀이 : 40분]
        투포인터, 길이 같아지면 L 증가시키면서 구간 좁히기
*/

class Solution {
    public int[] solution(String[] gems) {
        Set<String> type = new HashSet<>();
        for(String gem : gems){
            type.add(gem);
        }
        int jewl = type.size();
        
        int start = 1, end = gems.length;
        if(end == jewl) return new int[]{1, end};
        
        int minLen = end;
        
        int L = 0, R = 0;
        Map<String, Integer> basket = new HashMap<>();        
        while(R < gems.length){
            basket.put(gems[R], basket.getOrDefault(gems[R],0) + 1);
            
            if(basket.size() == jewl){
                while(basket.size() == jewl){
                    if(minLen > (R-L+1)){
                    minLen = R - L +1;
                    start = L + 1; end = R + 1;

                    }
                    basket.put(gems[L], basket.get(gems[L]) -1);
                    if(basket.get(gems[L]) == 0){
                        basket.remove(gems[L]);
                    }
                    L++;        
                }
            }
            R++;
        }
        return new int[]{start, end};
    }
}