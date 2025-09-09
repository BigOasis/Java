class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int dist = cur[2];

            if(row == n-1 && col == n-1) return dist;

            for(int i=0; i<8; i++) {
                int nextrow = row + dy[i];
                int nextcol = col + dx[i];

                if(nextrow>= 0 && nextrow<n && nextcol>=0 && nextcol<n && grid[nextrow][nextcol] == 0 && !visited[nextrow][nextcol]){
                    visited[nextrow][nextcol] = true;
                    q.offer(new int[]{nextrow, nextcol, dist+ 1});
                }
            }
        }

        return -1;
    }
}