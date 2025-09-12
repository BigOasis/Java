import java.util.*;

/*
    [스스로 풀이 : 17분] 13ms 45.6MB
        8방향 , 1부터 시작
        예외처리 먼저
        visited 안 두고 0 지나오면 그냥 1로 바꾸기
*/
class Solution {
    int[] dy = {-1,-1,-1,0,1,1,1,0}, dx={-1,0,1,1,1,0,-1,-1};
    int r,c;

    public int shortestPathBinaryMatrix(int[][] grid) {
        r = grid.length; c = grid[0].length;

        if(grid[0][0] ==1 || grid[r-1][c-1] ==1) {
            return -1;
        }
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1});
        grid[0][0] = 1;

        while(!q.isEmpty()){
            int[] cur = q.removeFirst();
            int y = cur[0], x = cur[1], count = cur[2];

            if(y == r-1 && x == c-1) return count;
            
            for(int d = 0; d< 8; d++){
                int ny = y + dy[d], nx = x + dx[d];

                if(inRange(ny,nx) && grid[ny][nx] == 0){
                    grid[ny][nx] = 1;
                    q.addLast(new int[]{ny,nx,count+1});
                }
            }
        }
        return -1;
    }
    private boolean inRange(int y, int x){
        return -1 < y && y < r && -1 < x && x < c;
    }
}