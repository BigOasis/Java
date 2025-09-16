import java.util.*;

/*
    양방향이므로 S,A,B를 출발점
    X를 합승하는 점으로 할 때
    S - X, X - A, X - B
    (S->X) + (A->X) + (B->X) 까지의 최소거리 
    출발점을 for문으로 S,A,B로 하고 dist 를 기록하는 배열도 3개, 
    n 돌면서 dist[0][X] + dist[1][X] + dist[2][X] 의 최소 
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        return answer;
    }
}