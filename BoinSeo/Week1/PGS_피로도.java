public class Main {
    // 결과값 변수 선언
    private static int answer = 0;
    // 방문 배열 선언
    private static boolean[] visited;


    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        solution(k, dungeons);
        System.out.println("answer = " + answer);
    }

    public static int solution(int k, int[][] dungeons) {
        // dfs 처리를 위한 방문 배열 초기화 처리
        visited = new boolean[dungeons.length];
        // dfs 시작
        dfs(k, dungeons, 0);
        // 결과값 리턴
        return answer;
    }

    // dfs, bfs 어떤게 더 나을까?
    // 경로에 관련된 문제니까, dfs가 좀더 직관적이지 않을까? 라는 생각
    public static void dfs(int k, int[][] dungeons, int clearCount) {
        // 최대값 갱신 조건 설정
        if (clearCount > answer) answer = clearCount;
        // 던전별 탐색 반복문 실행
        for (int i = 0; i < dungeons.length; i++) {
            // 방문한 던전이라면 건너뛰기
            if (visited[i]) continue;
            // 최소 필요 피로도
            int minNeedCost = dungeons[i][0];
            // 소모 피로도
            int useCost = dungeons[i][1];
            // 피로도가 던전을 입장하는 최소 피로도 보다 크다면
            if (k >= minNeedCost) {
                // 방문 처리 후
                visited[i] = true;
                // 피로도 소모가 계산된 값을 매개변수에 넣어주고, 클리어한 던전 카운트를 + 1 처리
                dfs(k - useCost, dungeons, clearCount + 1);
                // false 처리를 통한 백트랙킹 처리
                visited[i] = false;
            }
        }
    }
}