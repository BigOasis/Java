import java.util.*;

/*
  [스스로 풀이 : 30분] 0ms 46MB
    1. 왼 < 오 , 왼 > 오 경우가 있고 양 끝이 계속 바뀌니까 투포인터로 접근
    2. LW (왼쪽   가장 높은 벽), L(현재 기준 L)
    3. RW (오른쪽 가장 높은 벽), R(현재 기준 R)
    4. LW > L의 경우, RW > R의 경우
      움푹 들어가는 웅덩이가 발생하므로 각 높이를 누적
*/

class Solution {
    public int trap(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int LW = L, RW = R;
        int sum = 0;

        while(L < R){
          if(height[L] <= height[R]){
            L++;
            if(height[LW] <= height[L]){
              LW = L;
            }else{
              sum += height[LW] - height[L];
            }
          }
          else{
            R--;
            if(height[RW] <= height[R]){
              RW = R;
            }else{
              sum += height[RW] - height[R];
            }
          }
        }
        return sum;
    }
}