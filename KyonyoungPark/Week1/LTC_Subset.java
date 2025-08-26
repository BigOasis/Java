class Solution {
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        //k개 안채워도 빈 배열 시점에서 이미 추가하기
        result.add(new ArrayList<>(current));

        for(int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(i + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(0, nums, current, result);
        return result;
    }
}