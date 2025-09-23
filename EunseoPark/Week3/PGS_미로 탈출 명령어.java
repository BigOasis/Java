
class Solution {
    
    static int[] dy = {1,0,0,-1}, dx = {0,-1,1,0}; //d,l,r,u
    static char[] dir = {'d','l','r','u'};
    
    public String solution(int n, int m, int y, int x, int r, int c, int k) {
        if(!isPossible(y,x,r,c,k)) return "impossible";
        
        return make(n,m, y-1, x-1, r-1, c-1, k);
    }
    
    static String make(int n, int m, int y, int x, int r, int c, int k){
        StringBuilder sb = new StringBuilder();
        int cy =y, cx = x;
        
        while(k > 0){
            for(int d= 0; d< 4; d++){
                int ny = cy + dy[d], nx = cx + dx[d];
                
                if(!inRange(ny,nx,n,m)) continue;
                int dis = getDis(ny,nx,r,c);
                if(dis < k){
                    k--;
                    cy = ny;
                    cx = nx;
                    sb.append(dir[d]);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    static int getDis(int y, int x, int r, int c){
        return Math.abs(y-r) + Math.abs(x-c);
    }
    
    static boolean inRange(int y, int x, int n, int m){
        return -1 < y && y < n && -1 < x && x < m;
    }
    
    static boolean isPossible(int y, int x, int r, int c, int k){
        int dis = Math.abs(y-r) + Math.abs(x-c);
        if(dis > k) return false;
        
        else if(dis == k) return true;
        
        else{
            if((k-dis) % 2 == 0) return true;
            return false;
        }
    }
}

import java.util.*;

/*
    [스스로 풀이 : 6/31 나머지 시간 초과]
        중복 방문 가능하므로 그냥 BFS로 하면 K만큼 갈때까지 모든 칸 반복이므로 2500 X 2500 시간초과
        -> 예외처리 impossible
            1. 거리 > K
            2. 이미 도착했는데 남은 거리가 홀수
        그럼에도 남은 칸이 짝수칸이 남았는데 다 이동해야 함 -> du, lr 짝 지어서 붙여주면 되는데,
        현재 위치에따라 순서를 어떻게 하지...
*/

class Solution {
    
    static int[] dy = {1,0,0,-1}, dx = {0,-1,1,0}; //d,l,r,u
    static char[] dir = {'d','l','r','u'};
    
    static class Node{
        int y,x;
        StringBuilder sb;
        
        public Node(int y, int x, StringBuilder newSb){
            this.y = y; this.x = x;
            this.sb = new StringBuilder(newSb);
        }
        public String toString(){
            return y+" " + x + " sb : " + sb.toString();
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        // 거리 > k 
        if(getDist(y, x, r, c) > k) return "impossible";
        
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(x-1,y-1, new StringBuilder("")));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            // System.out.println(cur);
            
            for(int d = 0; d< 4; d++){
                int ny = cur.y + dy[d], nx = cur.x + dx[d];
                
                if(inRange(ny,nx, n, m)){
                    cur.sb.append(dir[d]);
                    // System.out.println("ny = " + ny + " nx = " + nx + "  " +  cur.sb.toString());
                    if(ny == r -1 && nx == c-1){
                        if((k - cur.sb.length()) % 2 != 0){
                            // System.out.println("이 경우" + " k = " + k + "  size = " +  cur.sb.length());
                            return "impossible";
                        }
                        if(k == cur.sb.length()) {
                            // System.out.println("b 경우");
                            return cur.sb.toString(); 
                        }
                        
                        //d
                        if(inRange(ny+1, nx, n,m)){
                            
                        }
                        //
                        
                    }
                    q.add(new Node(ny,nx,cur.sb));
                    cur.sb.deleteCharAt(cur.sb.length()-1);
                }
            }
        }
        return null;
    }
    static boolean inRange(int y, int x, int n, int m){
        return -1 < y && y < n && -1 < x && x < m;
    }
    
    static int getDist(int y, int x, int r, int c){
        return Math.abs(y-r) + Math.abs(x- c);
    }
}