import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        per(result, new ArrayList<>(), nums);
        return result;    
    }

    private void per(List<List<Integer>> result, List<Integer> cur, int[] nums){
        if(cur.size() == nums.length){
            result.add(new ArrayList<>(cur));
            return;
        }

        for(int i = 0, length = nums.length; i < length; i++){
            if(cur.contains(nums[i])) continue;
            cur.add(nums[i]);
            per(result, cur, nums);
            cur.remove(cur.size()-1);
        }
    }
}