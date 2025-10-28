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