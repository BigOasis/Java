
/*
    런타임 에러 -> Long
    소수 판별 때 36을 예시로 들면
    1 36
    2 18
    3 12
    4 9
    6
    으로 각각 나눠진다. 그럼 1~n까지 다 할 필요 없이 n의 제곱근까지만 하면 된다.
*/
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String newStr = Integer.toString(n, k);
        String[] split  = newStr.split("0+");
        
        for(String str : split){
            long num = Long.parseLong(str);
            if(num == 1) continue;
            
            boolean flag = true;
            for(int i = 2; i <= Math.sqrt(num); i++){
                if(num % i == 0){
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}