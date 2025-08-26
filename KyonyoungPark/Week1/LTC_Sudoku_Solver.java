class Solution {
    private char[][] board;
    private List<int[]> empty;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.empty = new ArrayList<>();

        // 빈 칸 찾기
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    empty.add(new int[]{i, j});
                }
            }
        }

        solve(0);
    }

    private boolean solve(int idx) {
        if(idx == empty.size()) {
            return true;
        }

        int x = empty.get(idx)[0];
        int y = empty.get(idx)[1];
        int x2 = (x / 3) * 3;
        int y2 = (y / 3) * 3;

        boolean[] written = new boolean[10];

        // 가로세로 검사
        for(int i = 0; i < 9; i++) {
            if(board[x][i] != '.') {
                written[board[x][i] - '0'] = true;
            }
            if(board[i][y] != '.') {
                written[board[i][y] - '0'] = true;
            }
        }

        // 소박스 검사
        for(int i = x2; i < x2 + 3; i++) {
            for(int j = y2; j < y2 + 3; j++) {
                if(board[i][j] != '.') {
                    written[board[i][j] - '0'] = true;
                }
            }
        }

        // 가능한 숫자들 시도
        for(int num = 1; num <= 9; num++) {
            if(!written[num]) {
                board[x][y] = (char)(num + '0');
                if(solve(idx + 1)) {
                    return true;  // 성공
                }
                board[x][y] = '.';  // 실패 시 다시 공백으로 롤백
            }
        }

        return false;  // 모든 숫자 시도해도 안 되면 실패
    }
}