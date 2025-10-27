import java.util.*;
// BFS(조건을 분리해놓은) 문제
// 지게차: 상하좌우중 한군데가 빈 곳을 추출
// 크레인: 조건없이 그냥 컨테이너 제거 가능

class Solution {
    static String[][] map;
    static boolean[][] visited;

    //델타함수
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int N, M;

    private void BFS() {
        visited = new boolean[N+ 2][M+ 2];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx >= 0 && nx <= N + 1 && ny >= 0 && ny <= M + 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny].equals("-1") || map[nx][ny].equals("0")) {
                        map[nx][ny] = "-1";
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    private void Forklift(String box) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j].equals(box)) {
                    boolean canRemove = false;
                    for (int k = 0; k < 4; k++) {
                        if (map[i + dx[k]][j + dy[k]].equals("-1")) {
                            canRemove = true;
                            break;
                        }
                    }
                    if (canRemove) {
                        map[i][j] = "0";
                    }
                }
            }
        }
    }

    private void Crane(String box) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j].equals(box)) {
                    map[i][j] = "0"; // 빈 공간
                }
            }
        }
    }

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();

        // 그래프에 경계만들기
        map = new String[N + 2][M+ 2];
        for (int i = 0; i < N+ 2; i++) {
            for (int j = 0; j < M+ 2; j++) {
                map[i][j] = "-1";
            }
        }

        // 창고 정보 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = String.valueOf(storage[i].charAt(j));
            }
        }

        // 요청 처리
        for (String request : requests) {
            BFS();

            if (request.length() == 2) {
                Crane(request.substring(0,1));
            } else {
                Forklift(request);
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!map[i][j].equals("-1") && !map[i][j].equals("0")) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}