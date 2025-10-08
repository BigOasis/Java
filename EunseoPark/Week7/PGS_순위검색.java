import java.util.*;

/*
    1. 탐색이므로, hm 이 가장 빠르다
        이 문제의 핵심은 key를 많이 만들지만, 한번 만들때 제대로 만들어서 탐색을 빠르게 하는 것
    2. hm<String, List<Integer>> string에는 
        java frontend junior pizza ->를 이어붙인 문자열을 key
        이때, -인 경우로 포함하여
        java-junior- -> 이런 경우로 key로 만든다
        
    2. hm의 value를 오름차순으로 정렬
        해당 key에 포함되는 지원자의 점수들이 value에 들어가는데, 오름차순으로 해야
        이분 탐색이 가능하다.
        [50,100,150,150] 이럴때, 점수 X가 120이라면 BS로 찾은 인덱스는 2,
        전체 value 사이즈 - 2 => 만족하는 지원자 수
    
*/

class Solution {
    public int[] solution(String[] info, String[] query) {
        int len = query.length;
        int[] answer = new int[len];
        Map<String, List<Integer>> hm = new HashMap<>();
        
        for(String i : info){
            String[] data = i.split(" ");
            String[] langs = {data[0], "-"};
            String[] jobs = {data[1], "-"};
            String[] exps = {data[2], "-"};
            String[] foods = {data[3], "-"};
            int value = Integer.parseInt(data[4]);
            
            for(String lang : langs){
                for(String job: jobs){
                    for(String exp : exps){
                        for(String food : foods){
                            String[] keydata = {lang, job, exp, food};
                            String key = String.join(" ", keydata);
                            hm.putIfAbsent(key, new ArrayList<>());
                            hm.get(key).add(value);
                        }
                    }
                }
            }
        }
        //2. hm value 오름차순 정렬
        for(List<Integer> v : hm.values()){
            Collections.sort(v);
        }
        
        int i = 0;
        //3.이분 탐색으로 찾고, 사이즈 - index
        for(String q : query){
            String[] data = q.split(" ");
            String[] keydata = {data[0], data[2], data[4], data[6]};
            int score = Integer.parseInt(data[7]);
            String key = String.join(" ", keydata);

            if(hm.containsKey(key)){
                List<Integer> vs = hm.get(key);
                answer[i] = vs.size()-bs(vs, score); 
            }
            i++;
            
        }
        
        return answer;
    }
    
    static int bs(List<Integer> v, int score){
        int s = 0, e = v.size()-1;
        
        while(s <= e){
            int mid = (s + e)/ 2;
            
            if(score <= v.get(mid)){ //동일하거나 작으면 내려가야지, 더 작은 값 찾으러,
                e = mid - 1;
            }else{
                s = mid + 1;
            }
        }
        return s;
        
    }
}