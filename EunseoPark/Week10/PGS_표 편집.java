import java.util.*;

/*  
    [다른 사람 풀이 참고]    
    1. 링크드리스트로 생각을 했는데
    1. LinkedList를 사용하면 해당 인덱스 위치로 이동해서 삽입하고 삭제할 때 O(N)의 시간 복잡도가 소요
        
        → prev, next 를 배열로 가지고 인덱스로 바로 접근한다
        
    2. 문제에서 표의 범위를 벗어날 수 없다고 해서 원형 순회로 맨 마지막에서 아래로 내려가면 맨 위로 올라가는 줄 알았는데 그 내용이 아니었음
*/
class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < n; i ++){
            prev[i] = i-1;
        }
        for(int i = 0; i < n; i++){
            next[i] = i + 1;
        }
        next[n-1] = -1;
        
        for(String c : cmd){
            int len = c.length();
            if(len > 1){
                char type = c.charAt(0);
                int move = Integer.parseInt(c.substring(2).trim());
                switch (type){
                    case 'U':
                        for(int i = 0; i < move; i++){
                            k = prev[k];
                        }
                        break;
                    case 'D':
                        for(int i = 0; i < move; i++){
                            k = next[k];
                        }
                        break;
                }
            }else{
                char type = c.charAt(0);
                switch(type){
                    case 'C':
                        alive[k] = false;
                        stack.addLast(k);
                        int p1 = prev[k];
                        int n1 = next[k];
                        if(n1 != -1){
                            prev[n1] = p1;
                        }
                        if(p1 != -1){
                            next[p1] = n1;
                        }
                        // 이 부분을 생각 못함. 기존에는 그냥 k가 n-1이면 이렇게 했는데 그 다음이 -1이면 즉, 현재가 실질적 마지막 노드이면, 위로 올라가기
                        if(next[k] != -1) k = next[k];
                        else k = prev[k];
                        
                        break;
                    case 'Z':
                        int pop = stack.removeLast();
                        alive[pop] = true;
                        int p2 = prev[pop];
                        int n2 = next[pop];
                        if(n2 != -1){
                            prev[n2] = pop;
                        }
                        if(p2 != -1){
                            next[p2] = pop;
                        }
                        break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <n; i++){
            if(alive[i]){
                sb.append("O");
            }else{
                sb.append("X");
            }
        }
        return sb.toString();
    }
}

import java.util.*;

/*
    [스스로 풀이 : 틀림]
        예제 테스트 케이스는 맞는데, 제출하면 다 틀림
        1. 삽입 삭제 -< 링크드리스트
        2. ctrl z 로 돌려야 하니까 stack 저장
*/
class Solution {
    public String solution(int n, int k, String[] cmds) {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            list.add(i);
        }
        Deque<int[]> stack = new ArrayDeque<>();
        
        for(String cmd : cmds){
            char type = cmd.charAt(0);
            if(cmd.length() == 3){
                int move = cmd.charAt(2) - '0';
                switch(type){
                    case 'D':
                        k = ( k + move ) % list.size();
                        break;
                    case 'U':
                        k -= move;
                        if(k < 0){
                            k += list.size();
                        }
                        break;
                }
            }else{
                switch(type){
                    case 'C':
                        if(list.size() == 0) continue;
                        int index = k;
                        int value = list.remove(k);
                        stack.addLast(new int[]{index, value});
                        if(k > list.size()){
                            k--;
                        }
                        break;
                    case 'Z':
                        if(stack.isEmpty()) continue;
                        int[] peek = stack.removeLast();
                        list.add(peek[0], peek[1]);
                        break;
                }
            }
        }
        String[] result = new String[n];
        for(int i = 0; i < 8; i++){
            result[i] = "O";
        }
        for(int[] arr : stack){
            int index = arr[0];
            result[index] = "X"; 
        }
        
        return String.join("",result);
    }
}