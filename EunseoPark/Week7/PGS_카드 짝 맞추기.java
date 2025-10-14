import java.util.*;

/*
    [정답 참고]
    1. 최단 거리 → 라는 말 듣고 bfs를 당연히 떠올렸어야 함
    2. 1. 1~6까지 숫자 중, 보드에 ‘남아있는’ 숫자를 찾는다
       2. 현재 카드에서 a→ b, b→a로 가는 최단거리를 구하고(bfs) 
        a→ b로 가는 방법, b→a로 가는 방법을 재귀로 해서 재귀반등, 
        즉 끝까지 가서 return 한 값들을 더해서 최종 최소를 구한다.    
*/
class Solution {
    static int[][] Board;
    static final int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    static final int INF = Integer.MAX_VALUE;
    
    static class Point{
        int y, x, cnt;
        
        public Point(int y, int x, int cnt){
            this.y = y; this.x = x; this.cnt = cnt;
        }
    }
    
    // 현재 위치 -> 목적지 최단 경로
    static int bfs(Point src, Point dst){
        boolean[][] visited = new boolean[4][4];
        visited[src.y][src.x] = true;
        Queue<Point> q = new ArrayDeque<>();
        q.add(src);
        
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            if(cur.y == dst.y && cur.x == dst.x){
                return cur.cnt;
            }
            
            for(int d= 0; d< 4; d++){
                int ny = cur.y + dy[d], nx = cur.x + dx[d];
                
                if(!inRange(ny,nx)) continue;
                
                // 그냥 한 칸 이동
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.add(new Point(ny,nx, cur.cnt + 1));
                }
                
                // ctrl 누르고 이동
                while(true){
                    if(Board[ny][nx] != 0) break;
                    if(!inRange(ny + dy[d], nx + dx[d])) break;
                    ny += dy[d]; nx += dx[d];
                }
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.add(new Point(ny,nx, cur.cnt + 1));
                }
                
            }
            
        }
        return INF;
    }
    
    static boolean inRange(int y, int x){
        return -1 < y && y < 4 && -1 < x && x < 4;
    }
    
    static int perm(Point src){
        int ret = INF;
        
        // 1~6 숫자중, 보드에 '남아있는' 숫자를 찾는다.
        // 1~6 까지 순서대로 방문이 아니다 {2,5} 만 남았다면 2 -> 5 방문하고, 5 -> 2로 방문하므로 순열 탐색이 됨.
        for(int num = 1; num <= 6; num++){
            List<Point> card = new ArrayList<>();
            
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(Board[i][j] == num){
                        card.add(new Point(i,j,0));
                    }
                }
            }
            if(card.isEmpty()) continue; // 이 숫자가 없다면 패스
            
            // (A) 같은 숫자 두 장을 A -> B 순서로
            int one = bfs(src, card.get(0))     // 현재 -> A
                + bfs(card.get(0), card.get(1)) // A -> B
                + 2;                            // Enter
            
            // (B) B -> A 순서로
            int two = bfs(src, card.get(1))     // 현재 -> B
                + bfs(card.get(1), card.get(0)) // B -> A
                + 2;
            
            // 해당 카드를 뒤집었으니 0으로
            for(int i = 0; i < 2; i++){
                Board[card.get(i).y][card.get(i).x] = 0;
            }
            
            // A -> B 순서로 뒤집고 이제 B에서 다시 출발
            ret = Math.min(ret, one + perm(card.get(1)));
            // B -> A 순서로 뒤집고 이제 A에서 다시 출발
            ret = Math.min(ret, two + perm(card.get(0)));
            
            // 재귀가 끝났으니, 보드 원상복구
            for(int i = 0; i < 2; i++){
                Board[card.get(i).y][card.get(i).x] = num;
            }
        }
        
        // for문에서 아무 숫자도 못 찾았다면 이동할 필요 -> 이제 0
        if(ret == INF) return 0;
        
        // 지금 단계에서 구한 최소 이동 수
        return ret;
    }
    
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return perm(new Point(r,c,0));
    }
}

import java.util.*;

/*
    [내 풀이 : 틀림] 
        결과 값이 다르게 나옴. 현재까지가 무조건 최소거리라고 판단했는데 
        풀다 보니, 다른 경로로 갔을때 더 최소 거리일 수도 있잖아..?
*/
class Solution {
    
    static int r, c;
    
    public int solution(int[][] board, int startR, int startC) {
        r = startR;
        c = startC;
        Map<Integer, List<int[]>> hm = new HashMap<>(); //카드 위치
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] == 0) continue;
                hm.putIfAbsent(board[i][j], new ArrayList<>());
                hm.get(board[i][j]).add(new int[]{i,j});
            }
        }
        
        // 카드 다 뒤집을 때까지 play
        int answer = 0;
        while(hm.size() > 0){
            //1. 현재 위치에 카드 없으면 최소 위치 카드 찾기
            if(board[r][c] == 0){
                answer += findNextCard(hm);
            }
            answer += 1; //enter;
            System.out.println("\nr = " + r + "   c = " + c + "  : " + answer);
            
            // 짝꿍 찾기
            List<int[]> values =  hm.get(board[r][c]);
            System.out.println(Arrays.deepToString(values.toArray()));
        
            for(int[] v : values){
                if(v[0] == r && v[1] == c) continue;
                answer += calDis(v[0], v[1]) + 1; //enter 포함
                System.out.println("짝궁 찾고 난 후 = " + answer);
                r = v[0]; c = v[1]; //위치 이동
                
                //카드 삭제
                hm.remove(board[r][c]);
                board[r][c] = 0;
                board[v[0]][v[1]] = 0;
            }
        }
        return answer;
    }
    static int findNextCard(Map<Integer, List<int[]>> hm){
        int nextR = -1, nextC = -1, dis = 16;
        
        for(List<int[]> value : hm.values()){
            for(int[] v : value){
                if(dis > calDis(v[0],v[1])){
                    dis = calDis(v[0], v[1]);
                    nextR = v[0]; nextC = v[1];
                }
            }
        }
        System.out.println("nextR : " + nextR + " nextC : " + nextC);
        r = nextR; c = nextC;
        return dis;
    }
    //enter 제외한 가는 것까지의 거리
    static int calDis(int y, int x){
        int dis = 0;
        
        if(y == r){
            dis += 0;
        }
        else if(y == 3 || y == 0){
            dis += 1;
        }else{
            dis += Math.abs(y - r);
        }
        if(x == c){
            dis += 0;
        }
        else if(x ==3 || x == 0){
            dis += 1;
        }else{
            dis += Math.abs(x - c);
        }
        return dis;
    }
}