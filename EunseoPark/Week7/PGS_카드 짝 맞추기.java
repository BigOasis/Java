import java.util.*;

/*
    [내 풀이] 
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