import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        //구슬 좌표 확인 <- 여기서 R B 비우고 움직이는 거 계산하는 건 좋은데
        //구슬끼리 부딪히는 걸 여기서 해결해야 하나
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    rx = i; ry = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    bx = i; by = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    static int bfs(int rx, int ry, int bx, int by) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{rx, ry, bx, by, 0});
        visited[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int crx = cur[0], cry = cur[1];
            int cbx = cur[2], cby = cur[3];
            int count = cur[4];

            if (count >= 10) {
                continue;
            }

            for (int dir = 0; dir < 4; dir++) {
                // 빨간 구슬과 파란 구슬을 선후관계에 맞게 굴리기....
                // 새 rx,ry && bx,by 좌표 결과물로 내놓으면 확인해보기 성공했는지
                // 여길 어떻게해야하지
                // rotate여기다가 쓸것.

                if (board[nbx][nby] == 'O') {
                    continue;
                }

                if (board[nrx][nry] == 'O') {
                    return 1;
                }


                if (visited[nrx][nry][nbx][nby]) {
                    continue;
                }

                visited[nrx][nry][nbx][nby] = true;
                q.offer(new int[]{nrx, nry, nbx, nby, count + 1});
            }
        }

        return 0; // 10번 안에 성공하지 못함
    }

    static int[] rotate(int x, int y, int dir) {
        int count = 0;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') {
                break;
            }

            x = nx;
            y = ny;
            count++;

            if (board[x][y] == 'O') {
                break;
            }
        }

        return new int[]{x, y, count};
    }
}