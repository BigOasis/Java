import java.util.*;

/*
  [스스로 풀이 오답, 정답 참고] 1ms 41MB
    k번째 숫자를 찾기 위해서 일반 permutation으로 하면 n! 까지 만들고어야 함 -> 시간초과
    시간최적화 -> 점프를 하면서 수학적 접근 가능

    1. k와 n을 갱신하는 과정에서 맨 마지막 2자리가 바꿈 (정답: 2314, 나: 2341)
    2. 초반 k-1을 하지 않아서 맨 마지막 1자리 차이로 맨 뒤자리 2자리의 차이가 났음.
*/
class Solution {
    public String getPermutation(int n, int k) {
      int[] fact = new int[n+1];
      fact[0] = 1;
      for(int i = 1; i <= n; i++){
        fact[i] = fact[i-1] * i;
      }
      StringBuilder sb = new StringBuilder();
      List<Integer> list = new ArrayList<>();
      for(int i = 0; i < n; i++){
        list.add(i + 1);
      }

      k = k -1; // 인덱스는 0부터 시작하므로 -1
      while(list.size()!=0){
        int share = k / fact[n-1];
        sb.append(list.get(share));
        list.remove(share);
        k = k % fact[n-1];
        n--;
      }      
      return sb.toString();
    }
}