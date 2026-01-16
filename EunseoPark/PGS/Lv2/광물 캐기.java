import java.util.*;

class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 99999999;
        
        Map<String,Integer> hm = new HashMap<>();
        String[] types = {"diamond", "iron", "stone"};
        for(int i = 0; i< 3; i++){
            hm.put(types[i], i);
        }
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{picks[0], picks[1], picks[2], 0, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int diamond = cur[0], iron = cur[1], stone = cur[2], curIdx = cur[3];
            
            // 문제 잘 보기: 다 돌지 않아도 남은 곡괭이 없을때의 피로도도 가능
            if((diamond ==0 && iron ==0 && stone ==0 )|| curIdx >= minerals.length){
                answer = Math.min(answer, cur[4]);
                continue;
            }
            
            for(int type = 0; type < 3; type++){
                if(cur[type] == 0) continue;
                int sum = cur[4];
                boolean flag = false;
                for(int i = 0; i < 5; i++){
                    int idx = curIdx + i;
                    if(idx == minerals.length) break;
                    
                    if(type - hm.get(minerals[idx]) <= 0){
                        sum += 1;
                    }
                    else if(type - hm.get(minerals[idx]) == 1){
                        sum += 5;
                    }else{
                        sum += 25;
                    }
                }
                switch(type){
                    case 0:
                        q.add(new int[]{diamond -1, iron, stone, curIdx + 5, sum});
                        break;
                    case 1:
                        q.add(new int[]{diamond, iron - 1, stone, curIdx + 5, sum});
                        break;
                    case 2:
                        q.add(new int[]{diamond, iron , stone - 1, curIdx + 5, sum});
                        break;
                }
            }
        }
        return answer;
    }
}