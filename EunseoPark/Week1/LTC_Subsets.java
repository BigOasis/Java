import java.util.*;

/*
    [정답 참고] 0ms 43.86MB
        sub 넘길때 now+1 로 시도했음
        -> 정답 참고 후 i + 1로 변경
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        sub(result, new ArrayList<>(), nums, 0);
        return result;
    }
    private void sub(List<List<Integer>> result, List<Integer> cur, int[] nums, int now){
        System.out.println(cur);
        result.add(new ArrayList<>(cur));

        for(int i = now, length = nums.length; i < length; i++){
            cur.add(nums[i]);
            sub(result, cur, nums, i+1);
            cur.remove(cur.size()-1);
        }
    }
}