// 그냥 '+, -' 경우 전부 다 만들어서 완성 시 합이 같으면 cnt늘리기
// 브루트 포스에 가까운 DFS

class Solution {
    static int answer = 0;
    private void DFS(int[] numbers, int idx, int target, int sum){
        if(idx == numbers.length){
            if(sum == target) {
                answer++;
            }
            return;
        }

        DFS(numbers, idx+1, target, sum+numbers[idx]);
        DFS(numbers, idx+1, target, sum-numbers[idx]);
    }
    public int solution(int[] numbers, int target) {
        DFS(numbers, 0, target, 0);
        return answer;
    }
}