import java.util.ArrayList;
import java.util.List;

public class Main {

    private static boolean found;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int width;
    private static int height;
    private static boolean[][] visited;

    public static void main(String[] args) {
        char[][] input1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        boolean exist1 = exist(input1, word1);
        System.out.println("exist1 = " + exist1);

        char[][] input2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word2 = "SEE";
        boolean exist2 = exist(input2, word2);
        System.out.println("exist2 = " + exist2);

        char[][] input3 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word3 = "ABCB";
        boolean exist3 = exist(input3, word3);
        System.out.println("exist3 = " + exist3);
    }

    public static boolean exist(char[][] board, String word) {
        width = board.length;
        height = board[0].length;
        found = false;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // GPT 가 알려준 지점 (1)
                if (board[i][j] != word.charAt(0)) continue;
                // 반복문을 돌때마다 초기화 시켜줘야함
                visited = new boolean[width][height];
                // 시작 지점은 방문 마킹
                visited[i][j] = true;
                // dfs 를 통해 만들어지는 문자열을 보기위한 list 선언
                List<Character> list = new ArrayList<>();
                list.add(board[i][j]);
                // dfs 시작
                dfs(i, j, list, board, word);
            }
        }
        // 결과값 리턴
        return found;
    }

    public static void dfs(int y, int x, List<Character> list, char[][] board, String word) {
        // 기저 조건 설정
        if (found) return;
        // dfs 탐색중, 찾는 단어의 길이가 같거나, 일치한다면 검색 종료
        if (list.size() == word.length()) {
            // 길이도 같은데, 찾는 문자열과 일치한다면 종료
            if (convertString(list).equals(word)) {
                found = true;
                return;
            }
            return;
        }
        // 사방 탐색 시작
        for (int i = 0; i < 4; i++) {
            // 위치 이동
            int moveY = y + dy[i];
            int moveX = x + dx[i];

            // 격자밖을 벗어낫는지 판단
            if (moveY < 0 || moveX < 0 || moveY >= width || moveX >= height) continue;
            // 방문한 지점인지 판단
            if (visited[moveY][moveX]) continue;
            // 마킹 처리
            visited[moveY][moveX] = true;
            // list 에 add 처리
            list.add(board[moveY][moveX]);

            // GPT 가 알려준 지점 (2)
            if (board[moveY][moveX] != word.charAt(list.size() - 1)) {
                list.remove(list.size() - 1);
                visited[moveY][moveX] = false;
                continue;
            }

            // 이동한 지점에서의 dfs 호출
            dfs(moveY, moveX, list, board, word);
            // 백트랙킹
            list.remove(list.size() - 1);
            visited[moveY][moveX] = false;
        }
    }

    // Charter List <> String 으로 변환
    private static String convertString(List<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (char ch : list) sb.append(ch);
        return sb.toString();
    }
}