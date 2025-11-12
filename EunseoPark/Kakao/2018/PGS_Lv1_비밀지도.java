class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i = 0; i < n; i++){
            // 숫자 그대로 비트 OR 연산자
            int combined = arr1[i] | arr2[i];
            
            // n자리수로 만들고 오른쪽에 붙임
            String binary = String.format("%" +n + "s", Integer.toBinaryString(combined));
            
            //1을 '#'으로, 0을 공백으로
            binary = binary.replace('1','#').replace('0', ' ');
            
            answer[i] = binary;
        }
        
        return answer;
    }
}