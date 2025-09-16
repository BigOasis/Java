import java.util.*;
/*
    [ 스스로 풀이 : 5분] 4ms 49MB
        visited 처음 들어갈 때 count 세기
*/
class Solution {
    int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    int r, c;

    public int numIslands(char[][] grid) {
        r = grid.length; c = grid[0].length;
        
        boolean[][] visited = new boolean[r][c];
        int count = 0;
        for(int i = 0; i < r; i ++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] =='1' && !visited[i][j]){
                    count++;
                    dfs(i,j, visited, grid);
                }
            }
        }
        return count;
    }
    private void dfs(int y, int x, boolean[][] visited, char[][] grid){
        visited[y][x] = true;

        for(int d = 0; d < 4; d++){
            int ny = y + dy[d], nx = x + dx[d];

            if(inRange(ny, nx) && grid[ny][nx]=='1' && !visited[ny][nx]){
                dfs(ny,nx, visited, grid);
            }
        }
    }
    private boolean inRange(int y, int x){
        return -1 < y && y < r && -1 < x && x < c;
    }
}

import java.util.*;
/*
    [ 참고 성능 최적화 ] 3ms 50MB
        visited를 두지 않고 grid 자체에서 이미 방문했으면 0으로 바꾸기
*/
class Solution {
    int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    int r, c;

    public int numIslands(char[][] grid) {
        r = grid.length; c = grid[0].length;
        int count = 0;

        for(int i = 0; i < r; i ++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] =='1'){
                    count++;
                    dfs(i,j, grid);
                }
            }
        }
        return count;
    }
    private void dfs(int y, int x, char[][] grid){
        grid[y][x] = '0';

        for(int d = 0; d < 4; d++){
            int ny = y + dy[d], nx = x + dx[d];

            if(inRange(ny, nx) && grid[ny][nx]=='1'){
                dfs(ny,nx, grid);
            }
        }
    }
    private boolean inRange(int y, int x){
        return -1 < y && y < r && -1 < x && x < c;
    }
}

