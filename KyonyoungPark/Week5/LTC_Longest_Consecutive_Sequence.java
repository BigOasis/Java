import java.util.*;

// Hash table관련 자료구조를 거의 안써봐서 몰랐는데
// HashMap은 값 저장해야해서 이점이 1도 없음. HashSet을 써야 한다네요.. ㅠ
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1) return nums.length ;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, 1);
        }

        int max = 0;
        int cur = 1;
        for(int num: map.keySet()){
            if(!map.containsKey(num -1)){
                cur = 1;
                while(map.containsKey(num+cur)) cur++;
                max = Math.max(max, cur);
            }
        }
        return max;
    }
}