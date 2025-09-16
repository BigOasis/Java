import java.util.*;

/*
    [풀이 참고] 953ms 55MB
        2개의 배열로 나눠서 한쪽에서 뽑은 값을 바탕으로 target과 가장 가깝게 되도록 오른쪽 배열에서 BS로 찾기
        -> 그럼 왼쪽 배열에서 뽑는 경우 2^15 가 됨.
*/

class Solution {
    static double min, target;
    static int N;

    public int minimumDifference(int[] nums) {
        N = nums.length;
        target = Arrays.stream(nums).sum()/2.0; //2로만 나누면 int로 바뀜. 소수점 날라가서 틀림
        min = Double.MAX_VALUE;

        List<List<Integer>> sumArray1 = getPartialSum(0, N/2, nums);
        List<List<Integer>> sumArray2 =  getPartialSum(N/2, N, nums);

        for(int i = 0, size = sumArray1.size(); i < size; i++){
            List<Integer> sum1 = sumArray1.get(i);
            List<Integer> sum2 = sumArray2.get(size - i - 1);

            for(int s1 : sum1){
                double targetDiff = target - s1;
                // sum1에서 뽑고 나머지 targetDiff와 가장 가까운 값을 sumArray2에서 BS로 찾기
                int index = Collections.binarySearch(sum2, (int) targetDiff);
                // 못 찾았다면 근사치 
                if(index < 0) {
                    index = ((index + 1) * -1);
                }
                // [3,9] 이고 찾는 값이 10이면 삽입 위치는 2가 됨. 
                // 즉 배열의 맨 뒤에 넣어야 함 -> 해당 배열의 값으로 값을 찾으려고 하면 없음
                if(index < sum2.size()){
                    min = Math.min(min, sum2.get(index) + s1 - target);
                }
            }
        }
        

        return Math.abs((int)(min * 2.0));
    }

    private List<List<Integer>> getPartialSum(int start, int end, int[] nums){
        int len = end - start;
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i <= len; i++){
            result.add(new ArrayList<>());
        }

        for(int mask = 0; mask < (1<<len); mask++){
            int sum = 0;
            for(int i = 0; i< len; i++){
                if((mask >> i & 1) == 1){
                    sum+= nums[start+i];
                }
            }
            int pickCount = Integer.bitCount(mask);
            result.get(pickCount).add(sum);
        }

        //BS위한 오름차순 정렬
        for(List<Integer> sums : result){
            Collections.sort(sums);
        }

        return result;
    }
}

/*  
    [스스로 풀이 : 1H, 시간초과] 
    2n의 길이 중 n개씩 2개 세트로 나눠서 세트간의 차이가 최소가 되도록 해야 함
    == 전체/2 = target으로 해서 한쪽 배열이 해당 target과의 차이가 최소가 되는 것을 찾고, 그 차이의 x2하면 세트간의 차이가 됨.

    1. target/2 일때 target자체가 홀수여서 .5로 나오는 경우를 생각 못함 -> int에서 double로 변경
    
    2. 134/201에서 시간초과 -> 거의 26개가 넘어감, 26C13이면 백트레킹 시작해야하는데 더 넘음


*/
class Solution {
    static double min, target;

    public int minimumDifference(int[] nums) {
        int n = nums.length;
        target = 0.0;

        min = Integer.MAX_VALUE;
        for(int num : nums){
            target += num;
        }
        target /=2.0;

        comb(new ArrayList<>() ,nums, n, 0);
        return (int)(min * (2.0));
    }
    private void comb(List<Integer> cur, int[] nums, int n, int start){
        if(cur.size() == n/2){
            cal(cur);
            return;
        }

        for(int i = start; i < n; i++){
            cur.add(nums[i]);
            comb(cur, nums, n, i+1);
            cur.remove(cur.size()-1);
        }
    }

    private void cal(List<Integer> cur){
        int sum = 0;
        for(int c : cur){
            sum += c;
        }
        double abs =  Math.abs(target - sum);
        min = Math.min(min,abs);
    }
}