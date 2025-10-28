// 스택형으로 삭제했던거 저장하기
// move들은 함수써서 알아서 삭제된 거 건너뛰게 구현하기

//1차 구현 - 런타임 문제 및 오답 발생
import java.util.*;

class Solution {
    static int curIdx;
    static int N;
    static boolean[] isDeleted;
    static Stack<Integer> stack = new Stack<>();
    private void move(String s, int X){
        int cnt = 0;
        if(s.equals("U")){
            while(curIdx > 0 && cnt<X){
                if(!isDeleted[curIdx]){
                    cnt++;
                }
                curIdx--;
            }
        }
        else{
            while(curIdx < N-1 && cnt<X){
                if(!isDeleted[curIdx]){
                    cnt++;
                }
                curIdx++;
            }
        }
    }

    private void delete(){
        stack.push(curIdx);
        isDeleted[curIdx] = true;
        if(curIdx == N-1) curIdx--;
        else curIdx++;
    }

    private void rollBack(){
        int idx = stack.pop();
        isDeleted[idx] = false;
    }

    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        N = n;
        curIdx = k;

        isDeleted = new boolean[N];
        for(String c: cmd){
            if(c.length() > 1){
                // 만약 D나 U 뒤에 두자리수오는 거 체크하기
                int x = Integer.parseInt(c.substring(2));
                move(c.substring(0,1), x);
            }
            else{
                if(c.equals("C")) delete();
                else rollBack();
            }
        }

        for(int i=0; i<N; i++){
            if(isDeleted[i]) sb.append("X");
            else sb.append("O");
        }

        answer = sb.toString();
        return answer;
    }
}