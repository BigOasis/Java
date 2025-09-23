import java.util.*;

/*
  [최적화 참고] 30ms 67MB
    1. 중복 제거 위해 hm
    2. 나는 현재부터 뒤로 가서 탐색했는데, 현재 -1 이 없는 출발점 숫자부터 시작하기
    3. -> if(!hm.containsKey(n-1)) 이면 +1 하면서 있을 때까지 찾기
*/
class Solution {
    public int longestConsecutive(int[] nums) {

      Map<Integer, Boolean> hm = new HashMap<>(); // value값은 필요없으나, byte가 가장 작은 boolean(1 byte)으로 채우기, int(4 byte)
      for(int n : nums){
        hm.put(n ,true);
      }

      int answer = 0;
      for(int n : hm.keySet()){
        if(!hm.containsKey(n - 1)){
          int count = 1;

          while(hm.containsKey(++n)){
            count++;
          }
          answer = Math.max(answer,count);
        }
      }
      return answer;
    }
}

import java.util.*;

/*
  [스스로 풀이 : 30분] 46ms 61MB
    1. 중복 제거 위해 hm
    2. find 이전 값 반복하면서 
       1) find 이미 방문 == 연결된 개수 이미 센 경우 :
          지금까지 count + find와 연결된 개수
       2) 처음 방문 :
          값 지금까지 값으로 업데이트 
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        int answer = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int num : nums){
          hm.put(num, -1);
        }

        for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
          int key = entry.getKey();
          int value = entry.getValue();
          if(value != -1) continue;
          int find = key - 1;
          
          while(true){
            if(hm.containsKey(find)){
              if(hm.get(find)==-1){
                hm.put(find, key - find);
              }
              else{
                int v = key - find + hm.get(find);
                hm.put(key, v);
                answer = Math.max(answer, v);
                break;
              }
            }else{
              int v = key - find;
              hm.put(key, v);
              answer = Math.max(answer, v);
              break;
            }
            find--;
          } 
        }
        return answer;
    }
}