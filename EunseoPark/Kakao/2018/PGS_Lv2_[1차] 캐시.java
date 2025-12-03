import java.util.*;

/*
    [스스로 풀이] : 시간 분배 실패
        그냥 LinkedList로 구현하면 100,000 * 30개가 되어서 시간복잡도 초과하지는 않겠다. -> 사실 더 최적화해야 한다고 생각했는데 지금 보니 그냥 해시로 찾고, LinkedList 돌면서 해도 100,000 * 30개만 되서 괜찮았다.
        캐시 사이즈 = N, 도시 = M
        실제 풀 때는 O(N*M) 이 아니라 O(M) 로 풀고 싶었다.
        따라서 큐에는 그냥 넣기만 하고, 이게 1일때만 유효한 값이고, 1 이상이면 빼면서 값을 -1 해주었다.
        
        => 실제에서는 그냥 완전 탐색 괜찮겠다. 생각하고 바로 풀고 시간 단축했어야 하는 문제
*/
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return 5 * cities.length;
        int answer = 0;
        
        Map<String,Integer> hm = new HashMap<>();
        Deque<String> q = new ArrayDeque<>();
        
        for(String city : cities){
            String cityName = city.toUpperCase();
            
            //miss
            if(!hm.containsKey(cityName)){
                answer += 5;
                if(hm.size() == cacheSize){
                    while(!q.isEmpty()){
                        String c = q.removeFirst();
                        if(hm.get(c) == 1){
                            hm.remove(c);
                            break;
                        }
                        else{
                            hm.put(c, hm.getOrDefault(c, 0) - 1);
                        }
                    }
                }
                q.addLast(cityName);
                hm.put(cityName, 1);
            }
            //hit
            else{
                answer += 1;
                q.addLast(cityName);
                hm.put(cityName, hm.getOrDefault(cityName,0) + 1);
            }
            
        }
        return answer;
    }
}

/*
    그냥 LinkedList로 구현하면 100,000 * 30개가 되어서 시간복잡도 초과하지는 않겠다.
    먼저 위의 방식대로 푼 후 다시 LinkedList로 구현
    위의 방식이 O(M)으로 더 빠르긴 하지만, 실제 테스트 결과 둘 다 통과, 시간 차이 크지 않음
*/
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return 5 * cities.length;
        int answer = 0;
        
        List<String> list = new LinkedList<>();
        Set<String> cache = new HashSet<>();
        
        for(String city : cities){
            String cityName = city.toUpperCase();
            
            // miss
            if(!cache.contains(cityName)){
                answer += 5;
                
                if(cache.size() == cacheSize){
                    cache.remove(list.remove(0));
                }
                cache.add(cityName);
                list.add(cityName);
            }
            // hit
            else{
                answer += 1;
                list.remove(cityName);
                list.add(cityName);
            }
        }
        return answer;
    }
}