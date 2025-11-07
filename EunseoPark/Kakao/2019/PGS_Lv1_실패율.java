import java.util.*;

/*
    [스스로 풀이 : 30분] 2019 카카오 lv.1
        1. 처음 문제 이해하는데 잘못함 TreeMap으로하려고 했는데 key는 중복안되므로 0.5, 0.5 인 경우 하나가 없어짐
        -> class 생성해서 비교
        [compareTo] 
            1 : a > b 
           -1 : a < b
            0 : a == b
*/
class Solution {
    static class Miss implements Comparable<Miss>{
        double miss; int index;
        
        public Miss(double miss, int index){
            this.miss = miss; this.index =index;
        }
        public int compareTo(Miss o){
            if(this.miss == o.miss){
                return this.index - o.index;
            }
            if(this.miss > o.miss){
                return -1;  //음수면 a < b
            }
            return 1;       //1이면 a > b
        }
    }
    public int[] solution(int N, int[] stages) {
        Arrays.sort(stages);
        
        int[] cnt = new int[N+2];
        for(int stage : stages){
            cnt[stage]++;
        }
        
        PriorityQueue<Miss> pq = new PriorityQueue<>();
        int total = stages.length;
        
        for(int i = 1; i <= N; i++){
            if(total == 0) {
                pq.add(new Miss(0, i));
                continue;
            }
            double miss = cnt[i] / (double) total;
            total -= cnt[i];
            pq.add(new Miss(miss, i));
        }
        int[] answer = new int[N];
        for(int i = 0; i < N; i++){
            answer[i] = pq.poll().index;
        } 
        return answer;
    }
}