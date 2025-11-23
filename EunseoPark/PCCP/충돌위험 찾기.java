import java.util.*;

/*
    [스스로 풀이]
        처음에 목적지가 한개라고 생각함. 그러나 목적지가 여러개인 경우를 고려해야 함 -> 코드 다 지우고 다시 2차 풀이
*/
class Solution {
    
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Map<Integer, int[]> posHm = new HashMap<>();
        for(int i =0; i < points.length; i++){
            posHm.put(i+1, points[i]);
        }
        Map<Integer,List<Integer>> moveHm = new HashMap<>();
        Deque<int[]> q = new ArrayDeque<>();
        
        for(int i = 0; i < routes.length; i++){
            int start = routes[i][0];
            moveHm.putIfAbsent(i + 1, new ArrayList<>());
            
            for(int j = 2; j < routes[0].length; j++){
                moveHm.get(i+1).add(routes[i][j]);
            }
            int sy = posHm.get(start)[0], sx = posHm.get(start)[1];
            int ey = posHm.get(routes[i][1])[0], ex = posHm.get(routes[i][1])[1];
            q.add(new int[]{i+1, sy,sx,ey,ex,getDis(sy,sx,ey,ex)});
        }
        
        while(!q.isEmpty()){
            Map<String, Integer> countHm = new HashMap<>();
            int len = q.size();
            
            for(int i = 0 ; i < len; i++){
                int[] cur = q.poll();
                int num = cur[0], cy = cur[1], cx = cur[2], ey = cur[3], ex = cur[4], dis = cur[5];
                String str = cy +"," + cx;
                countHm.put(str, countHm.getOrDefault(str, 0) + 1);
                
                if(cy == ey && cx == ex){
                    if(moveHm.get(num).size() == 0){
                        continue;
                    }else{
                        int next = moveHm.get(num).get(0);
                        moveHm.get(num).remove(0);
                        ey = posHm.get(next)[0]; ex = posHm.get(next)[1];
                        dis = getDis(cy,cx,ey,ex);
                    }
                }
                
                for(int d = 0; d< 4; d++){
                    int ny = cy + dy[d], nx = cx + dx[d];
                    
                    if(!inRange(ny,nx)) continue;
                    if(getDis(ny,nx,ey,ex) < dis){
                        q.add(new int[]{num, ny,nx,ey,ex,dis-1});
                        break;
                    }
                }
            }
            for(String key : countHm.keySet()){
                if(countHm.get(key) > 1){
                    answer++;
                }
            }
            
        }
        
        return answer;
    }
    
    static int getDis(int sy, int sx, int ey, int ex){
        return Math.abs(sy-ey) + Math.abs(sx-ex);
    }
    
    static boolean inRange(int y, int x){
        return -1 < y && y < 101 && -1 < x && x < 101;
    }
}