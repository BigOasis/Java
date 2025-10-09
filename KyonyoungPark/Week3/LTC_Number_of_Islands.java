class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int cnt = 0;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        for(int i =0; i< row; i++){
            for(int j =0; j<col; j++){
                if(grid[i][j] == '1'){
                    cnt++;

                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i, j});
                    grid[i][j] = '0';

                    while(!q.isEmpty()) {
                        int[] cur = q.poll();

                        int old_row = cur[0];
                        int old_col = cur[1];

                        for(int a =0 ; a<4; a++){
                            int new_row = old_row + dy[a];
                            int new_col = old_col + dx[a];

                            if(new_row>=0 && new_row<row && new_col>=0 && new_col<col && grid[new_row][new_col] == '1'){
                                grid[new_row][new_col] = '0';
                                q.offer(new int[]{new_row, new_col});
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}