import java.util.*;
/*
    [1차 스스로 풀이] 163ms / 42.06 MB
        1. 최단거리 문제는 bfs가 더 빠르겠지만, 여기서는 순서대로 밟고 가야 하는 경로가 있으므로 dfs 
        2. 보완할 점
            1) exist함수에서 이중 for문으로 검사하는 것이 아닌 바로 dfs 호출하기 -> 기억에 안남
            2) 재귀함수 호출하면서 return 하는 값을 잘 처리하고 싶음.
*/
class Solution {
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    static int r,c;

    public boolean exist(char[][] board, String word) {
        r = board.length; c = board[0].length;
        boolean[][] visited = new boolean[r][c];
        int count = 0;
        boolean result = false;

        for(int i = 0 ; i < r; i++){
            for(int j = 0 ; j < c; j++){
                if(!visited[i][j] && word.charAt(count) == board[i][j]){
                    visited[i][j] = true;
                    result = dfs(count+1, i, j, word, visited, board);
                    System.out.println("\n");
                    if(result) return result;
                    visited[i][j] = false;
                }
            }
        }
        return result;
    }
    private boolean dfs(int count,int y, int x, String word, boolean[][] visited, char[][] board){
        if(count == word.length()) return true;
        boolean result = false;

        for(int d =0; d <4; d++){
            int ny = y + dy[d], nx = x + dx[d];

            if(inRange(ny,nx) && !visited[ny][nx] && word.charAt(count) == board[ny][nx]){
                visited[ny][nx] = true;
                result = dfs(count + 1, ny, nx, word, visited,board);
                if(result)  return result;
                visited[ny][nx] = false;
            }      
        }
        return result;
    }

    private boolean inRange(int y, int x){
        return -1 < y && y < r && -1 < x && x <c;
    }
}


import java.util.*;
/*
    [2차 최적화 코드 참고] 149ms / 41.62 MB
        1. visited 배열 삭제, board 자체를 변경
        2. 불필요 변수 count, result 제거

*/
class Solution {
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    static int r,c;

    public boolean exist(char[][] board, String word) {
        r = board.length; c = board[0].length;

        for(int i = 0 ; i < r; i++){
            for(int j = 0 ; j < c; j++){
                if(word.charAt(0) == board[i][j]){
                    if(dfs(1, i, j, word, board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(int count,int y, int x, String word, char[][] board){
        if(count == word.length()) return true;

        char tmp = board[y][x];
        board[y][x] = '#';

        for(int d =0; d <4; d++){
            int ny = y + dy[d], nx = x + dx[d];

            if(inRange(ny,nx) && board[ny][nx]!='#' && word.charAt(count) == board[ny][nx]){
                if(dfs(count + 1, ny, nx, word, board)){
                    return true;
                }
            }      
        }
        board[y][x] = tmp;
        return false;
    }

    private boolean inRange(int y, int x){
        return -1 < y && y < r && -1 < x && x <c;
    }
}