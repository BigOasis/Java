import java.util.*;

class Solution {
    private int[] dy = {-1, 1, 0, 0};
    private int[] dx = {0, 0, -1, 1};
    private int answer = Integer.MAX_VALUE;

    private boolean isValid(int y, int x) {
        return y >= 0 && y < 4 && x >= 0 && x < 4;
    }
    // ctrl 구현
    private int[] ctrlMove(int[][] board, int y, int x, int dir) {
        int ny = r;
        int nx = c;

        while (true) {
            nr += dr[dir];
            nc += dc[dir];

            if (!isValid(nr, nc)) {
                nr -= dr[dir];
                nc -= dc[dir];
                break;
            }

            // 카드 발견하면 카드로 가기
            if (board[nr][nc] != 0) {
                break;
            }
        }

        return new int[]{nr, nc};
    }
    // 일단 최단 거리 찾는 건 필요함.
    // BFS로 구현
    private int bfs(int[][] board, int sy, int sx, int ey, int ex) {
        if (sy == ey && sx == ex) return 0;

        boolean[][] visited = new boolean[4][4];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sy, sx, 0});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = tmp[0] + dy[i];
                int nx = tmp[1] + dx[i];

                if (isValid(ny, nx) && !visited[ny][nx]) {
                    if (ny == ey && nx == ex) return tmp[2] + 1;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx, tmp[2] + 1});
                }

                // Ctrl 이동 (카드나 끝까지)
                int[] ctrl = ctrlMove(board, tmp[0], tmp[1], i);
                ny = ctrl[0];
                nx = ctrl[1];

                if (!visited[ny][nx]) {
                    if (ny == ey && nx == ex) return tmp[2] + 1;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx, tmp[2] + 1});
                }
            }
        }

        return -1;
    }

    public int solution(int[][] board, int r, int c) {
        List<Integer> cardTypes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0 && !cardTypes.contains(board[i][j])) {
                    cardTypes.add(board[i][j]);
                }
            }
        }

        // 여기서 출발 지점에서 카드 찾는 순서를 전부 다해봐야? 하는듯 BFS 적용시킬 때
        return answer;
    }
}