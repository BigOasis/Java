import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 값, idx 저장용
        int[][] arr = new int[nums.length][2];
        for(int i = 0; i<nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        // 투 포인터로 탐색 위해 정렬
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);


        int left = 0, right = nums.length-1;
        // 정답 배열
        int[] answer = null;
        while(left <right) {
            int sum = arr[left][0] + arr[right][0];
            // 맞는 짝꿍을 찾으면 return
            if (sum == target) {
                answer = new int[]{arr[left][1], arr[right][1]};
                return answer;
            }
            else if (sum < target){
                left++;
            }
            else right--;
        }
        return answer;
    }
}