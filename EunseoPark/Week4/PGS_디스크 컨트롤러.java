import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // jobs 요청 시각 빠른 순서 오름차순 정렬
        Arrays.sort(jobs, (a,b) -> {
            return a[0]-b[0]; 
        });
             
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]); // 소요시간
        int completeJob = 0;
        int totalJob = jobs.length;
        int nowCount = 0;
        int sum = 0;
        int currentTime = 0;
        
        while(completeJob < totalJob ){
            while(nowCount < totalJob && currentTime >= jobs[nowCount][0]){
                pq.add(jobs[nowCount++]);             
            }
            if(!pq.isEmpty()){
                completeJob++;
                int[] cur = pq.poll();
                currentTime += cur[1];
                sum += currentTime - cur[0];
            }else{
                currentTime = jobs[nowCount][0];
            }
            
        }
        
        return (int) sum /totalJob ;
    }
}