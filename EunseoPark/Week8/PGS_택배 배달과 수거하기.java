import java.util.*;

/*
    [내 풀이]
        1. 완전 탐색 X
        2. 맨 뒤부터 확인
            1) 트럭에 실을 택배 : max(cap, deliveries[i])
    [아이디어 참고 후]
        1. 제일 멀리 있는 것부터 방문 -> 스택의 꼭대기
        2. 배달, 수거가 0이 아닌 집 중에 제일 멀리 있는 것을 가는 과정에서 
            배달, 수거 or 수거,배달 ->을 하면서 순서가 바뀌어도 어쨌든 맨 끝까지만 가면 됨
            즉, 스택의 맨 꼭대기에 있는 집 번호만 2배(왕복)로 누적하면 되고, 순서는 신경 X
        3. 배달, 수거 스택 꼭대기 중 집 번호가 더 높은 것 중 집 번호가 더 높은 것
*/
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Deque<Integer> del = new ArrayDeque<>();  //택배
        Deque<Integer> pick = new ArrayDeque<>(); //수거
        
        for(int i = 0 ; i< n; i++){
            if(deliveries[i] == 0) continue;
            del.addLast(i);
        }
        for(int i = 0; i < n; i++){
            if(pickups[i] == 0) continue;
            pick.addLast(i);
        }
        int dis = 0;
        while(true){
            if(del.isEmpty() && pick.isEmpty()) break;
            if(del.isEmpty()){
                dis += (pick.peekLast() + 1) * 2;
            }
            else if(pick.isEmpty()){
                dis += (del.peekLast() + 1) * 2;
            }
            else{
                dis += (Math.max(del.peekLast(), pick.peekLast()) + 1 ) * 2; 
            }
            
            if(!del.isEmpty()){
                 int putPercel = cap;        
                 while(putPercel > 0 && !del.isEmpty()){
                    int peek = del.peekLast();
                    if(putPercel >= deliveries[peek]){
                        putPercel -= deliveries[peek];
                        del.removeLast();
                    }
                    else{
                        deliveries[peek] -= putPercel;
                        putPercel = 0;
                    }
                }
            }
           
            if(!pick.isEmpty()){
                int getPercel = cap;
                 while(getPercel > 0 && !pick.isEmpty()){
                    int peek = pick.peekLast();
                    if(getPercel >= pickups[peek]){
                        getPercel -= pickups[peek];
                        pick.removeLast();
                    }else{
                        pickups[peek] -= getPercel;
                        getPercel = 0;
                    }
                }
            }
        }
        return dis;
    }
}