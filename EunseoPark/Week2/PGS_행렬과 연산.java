import java.util.*;

/*
    [기존 풀이] 
        행, 열 값을 하나씩 미뤄서 이동 시도 -> 시간초과 
        1. 한번 이동 O(연산 K x 전체 칸 수 (R x C))
            = 10^5 x 10^5
    [정답 참고]
        행 단위/열 테두리를 deque로 분해해 회전
        => O(K + N)
        k번 연산, 마지막 합칠 때 N
*/
class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Deque<Integer>> mid = new ArrayDeque<>();
        Deque<Integer> right = new ArrayDeque<>();
        int r = rc.length;
        int c = rc[0].length;
        
        // 1. 넣기 [1,2,3]
        for(int[] row : rc){
            left.addLast(row[0]);
            Deque<Integer> midQ = new ArrayDeque<>();
            
            for(int i = 1; i < c - 1; i++){
                midQ.addLast(row[i]);
            }
            mid.addLast(midQ);
            right.addLast(row[c-1]);
        }
        
        for(String op : operations){
            if(op.equals("Rotate")){
                rotate(left, mid, right);
            }else{
                shiftRow(left, mid, right);
            }
        }
        // 3. 합치기
        int[][] answer = new int[r][c];
        for(int i = 0; i < r; i++){
            answer[i][0] = left.removeFirst();
            int j = 1;
            for(int value : mid.removeFirst()){
                answer[i][j++] = value;
            }
            answer[i][j] = right.removeFirst();
        }
        
        return answer;
    }
    private void rotate(Deque<Integer> left, Deque<Deque<Integer>> mid, Deque<Integer> right){
        Deque<Integer> midFirst = mid.peekFirst();
        Deque<Integer> midLast = mid.peekLast();
        
        midFirst.addFirst(left.removeFirst());
        right.addFirst(midFirst.removeLast());
        midLast.addLast(right.removeLast());
        left.addLast(midLast.removeFirst());
        
    }
    
    private void shiftRow(Deque<Integer> left, Deque<Deque<Integer>> mid, Deque<Integer> right){
        left.addFirst(left.removeLast());
        mid.addFirst(mid.removeLast());
        right.addFirst(right.removeLast());
    }
}