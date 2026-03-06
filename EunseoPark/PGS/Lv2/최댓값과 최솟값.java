import java.util.*;

class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int[] intArr = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++){
            intArr[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(intArr);
        
        String answer = intArr[0] +" " + intArr[arr.length-1];
        return answer;
    }
}
