class Solution {
    private int n;
    private boolean[] col;
    private boolean[] right;
    private boolean[] left;
    private List<List<String>> result;
    private int[] queens; // 정답용

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.col = new boolean[n];
        this.right = new boolean[2 * n - 1];
        this.left = new boolean[2 * n - 1];
        this.result = new ArrayList<>();
        this.queens = new int[n];

        queen(0);
        return result;
    }

    private void queen(int row) {
        if(row == n) {
            result.add(makeBoard());
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!(col[i] || right[row + i] || left[row - i + n - 1])) {
                col[i] = right[row + i] = left[row - i + n - 1] = true;
                queens[row] = i; // 현재 행의 퀸 위치 저장
                queen(row + 1);
                col[i] = right[row + i] = left[row - i + n - 1] = false;
            }
        }
    }

    private List<String> makeBoard() {
        List<String> board = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(j == queens[i]) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            board.add(sb.toString());
        }
        return board;
    }
}