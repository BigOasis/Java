class Solution {
    private void backtrack(int start, int n, int k, int index, int[] current, List<List<Integer>> result) {
        //마지막 index까지 왔으면 배열 구성을 완료했으므로 result에 추가
        if(index == k) {
            List<Integer> combination = new ArrayList<>();
            for(int num : current) {
                combination.add(num);
            }
            result.add(combination);
            return;
        }

        // 요구하는 조합에 맞는 수를 더 추가할 수 없는 경우는 자르기
        if(index + (n - start + 1) < k) {
            return;
        }

        for(int i = start; i <= n; i++) {
            current[index] = i;
            backtrack(i + 1, n, k, index + 1, current, result);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, current, result);
        return result;
    }
}