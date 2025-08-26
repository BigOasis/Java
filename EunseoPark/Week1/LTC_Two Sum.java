import java.util.*;
/* 
    [ 1차 스스로 풀이 ]
    1. 탐색이라 O(1)인 해쉬맵이 떠오름
        1. (값, 인덱스) 저장 -> O(N)
        2. i 돌면서 target - nums[i] && 자기 자신이 아니면 return 해당 값 -> O(N)
        = O(N) + O(N) = O(2N)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hm = new HashMap<>();
        int length = nums.length;
        for(int i = 0; i< length; i++){
            hm.put(nums[i], i);
        }
        for(int i = 0; i < length; i++){
            int t = target - nums[i];
            if(hm.containsKey(t) && hm.get(t) != i){
                return new int[]{i, hm.get(t)};
            }
        }
        return new int[]{};
    }
}

import java.util.*;
/* 
    [ 2차 다른 코드 참고 후 최적화 ]
    1. 탐색이라 O(1)인 해쉬맵이 떠오름
        1차 스스로 풀이에서 2번 예제의 자기 자신 제외를 저렇게 처리하지 말고
        1. 넣을때 target -nums[i] 가 있다면 바로 return
        2. 없으면 해시 값에 추가
        = O(N)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hm = new HashMap<>();

        for(int i = 0, length = nums.length; i< length; i++){
            int t = target - nums[i];
            if(hm.containsKey(t)){
                return new int[]{i, hm.get(t)};
            }
            hm.put(nums[i], i);
        }
        return new int[]{};
    }
}

