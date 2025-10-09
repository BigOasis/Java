import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[rooms.size()];

        stack.push(0);
        visited[0] = true;

        while(!stack.isEmpty()) {
            int tmp = stack.pop();

            for(int key: rooms.get(tmp)){
                if(!visited[key]) {
                    visited[key] = true;
                    stack.push(key);
                }
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}