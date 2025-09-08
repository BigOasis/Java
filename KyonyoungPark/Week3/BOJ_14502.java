import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        build(0);

        System.out.println(max);
    }

    // 빈칸이 최대 58개 언저리라서 30개중 3개 뽑는 combination에 대해서만 다 검사하면 된다.
    // 30856 * 큐에는 최대 64개들어가기 때문에 넉넉함
    static void build(int cnt) {
        if (cnt == 3) {
            int tmp = spread();
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽 치고 테스트
                    build(cnt + 1);
                    map[i][j] = 0; // 원복
                }
            }
        }
    }

    // BFS로 안전구역 확인하기
    static int spread() {
        // 맵 복사
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp[i] = map[i].clone();
        }

        // BFS로 바이러스 퍼뜨리기
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        // BFS 실행(바이러스 살포)
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // 0 세기
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}