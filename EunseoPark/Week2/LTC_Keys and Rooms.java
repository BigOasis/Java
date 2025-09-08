import java.util.*;

/*
    [스스로 풀이 : 15분] 2ms, 44MB
        1. dfs가 먼저 생각났지만 stack 주차라 stack 으로 생각
        2. visited로 중복 탐색 방지
*/
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int count = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[rooms.size()];
        queue.addLast(0);

        while(!queue.isEmpty()){
            int nows = queue.removeFirst();
            if(visited[nows]) continue;
            visited[nows] = true;
            count++;
            
            for(int now : rooms.get(nows)){
                queue.addLast(now);
            }
        }
        
        return count == rooms.size();
    }
}