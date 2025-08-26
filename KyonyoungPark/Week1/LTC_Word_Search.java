class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    // dx, dy 쓰기 편하게 더 넓은 보드
    public static char[][] new_board;
    private boolean dfs(char[][] board, String word, int i, int j, int cnt, boolean[][] visited){
        // 탐색 성공
        if(cnt == word.length()) return true;

        // 영역 밖 or 찾는 문자가 아님
        if(board[i][j] == '#' ||visited[i][j] ||
                board[i][j] != word.charAt(cnt)) {
            return false;
        }

        visited[i][j] = true;

        // 델타 함수로 추적
        for(int k = 0; k<4; k++){
            if(dfs(board, word, i+dy[k], j+dx[k] ,cnt+1 ,visited)){
                visited[i][j] = false;
                return true;
            };
        }

        visited[i][j] = false;
        // 못 찾았으면 끝까지 false
        return false;
    }
    public boolean exist(char[][] board, String word) {
        int y= board.length, x= board[0].length;
        boolean[][] visited = new boolean[y+2][x+2];
        // 여기도 사이즈 맞춰서 설정
        new_board = new char[y+2][x+2];

        // 영역 만들기
        for(int i = 0; i<x+2; i++){
            new_board[0][i] = '#';
            new_board[y+1][i] = '#';
        }
        for(int i = 1; i<y+1; i++){
            new_board[i][0] = '#';
            for(int j = 1; j<x+1; j++){
                new_board[i][j] = board[i-1][j-1];
            }
            new_board[i][x+1] = '#';
        }

        for(int i= 1; i<y+1; i++){
            for(int j = 1; j<x+1; j++) {
                if(new_board[i][j] == word.charAt(0)){
                    if(dfs(new_board, word, i, j, 0, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}