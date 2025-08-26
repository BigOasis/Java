import java.util.*;

public class LTC_TwoSum {

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("Arrays.toString(result1) = " + Arrays.toString(result1));

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("Arrays.toString(result2) = " + Arrays.toString(result2));

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = twoSum(nums3, target3);
        System.out.println("Arrays.toString(result3) = " + Arrays.toString(result3));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            // value 값 선언
            int value = nums[index];
            // 보수 값 선언
            int need = target - value;
            // 만약 보수값이 존재한다면, 문제의 조건은 무조건 1개의 해답이 있다고 가정되어있음
            // return 처리
            if (map.containsKey(need)) {
                // 이부분이 가장 헷갈렸는데, 리스트에서의 첫번째 값을 꺼내는 이유는
                // 보수의 값을 찾는 시점에서의 가장 빠른 위치에 존재하는 값이기 때문
                result[0] = map.get(need).get(0);
                result[1] = index;
                return result;
            }
            // 만약 키값이 존재하다면, 해당 키값에 해당하는 List 를 반환하고
            // 그렇지 않다면 해당 키값에 맞는 빈 List 를 put 처리
            map.computeIfAbsent(value, k -> new ArrayList<>()).add(index);
        }
        return result;
    }
}