import java.util.*;
/*
    [스스로 풀이] 0ms 43.52MB
*/
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;

        while(l <= r){
            int mid = (l+r)/2;
            if(nums[mid] == target) return mid;

            else if(nums[mid] < target){
                l = mid + 1;
            }
            else{
                r = mid -1;
            }
        }
        return -1;
    }
}
import java.util.*;
/*
    [2차 메서드] 0ms 45.88MB
        이거 메서드있었는데 해서 메서드 바로 적용
*/
class Solution {
    public int search(int[] nums, int target) {
        int result = Arrays.binarySearch(nums, target);

        if(result < 0) return -1;
        return result;
    }
}