import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        
        for(String[] bt : book_time){
            bt[0] = bt[0].replace(":","");
            String[] endT = bt[1].split(":");
            int endH = Integer.parseInt(endT[0]);
            int endM = Integer.parseInt(endT[1]);
            
            endM += 10;
            if(endM >= 60){
                endM -= 60;
                endH += 1;
            }
            
            bt[1] = String.format("%02d", endH) + String.format("%02d", endM);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();        
        Arrays.sort(book_time, (a,b) -> a[0].compareTo(b[0]));
        int room = 0;
        
        for(String[] bt : book_time){
            int start = Integer.parseInt(bt[0]);
            int end = Integer.parseInt(bt[1]);
            
            if(pq.isEmpty() || pq.peek() > start){
                room++;
                pq.add(end);
                continue;
            }
            pq.poll();
            pq.add(end);
            
        }
        return room;
    }
}