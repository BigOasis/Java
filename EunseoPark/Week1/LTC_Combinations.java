import java.util.*;
/*
    [1차 스스로 풀이] 63ms 94.56MB
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        comb(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    private void comb(List<List<Integer>> result, List<Integer> cur, int n, int k, int now){
        if(cur.size() == k) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for(int i = now; i <= n; i++){
            if(cur.contains(i)) continue;
            cur.add(i);
            comb(result, cur, n, k, i);
            cur.remove(cur.size()-1);
        }
    }
}


import java.util.*;
/*
    [2차 성능 최적화 참고] 18ms 94.56MB
        1. contains -> O(N^2) 소요를 제거
        2. i => i + 1을 넘겨 중복 방지
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        comb(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    private void comb(List<List<Integer>> result, List<Integer> cur, int n, int k, int now){
        if(cur.size() == k) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for(int i = now; i <= n; i++){
            cur.add(i);
            comb(result, cur, n, k, i + 1);
            cur.remove(cur.size()-1);
        }
    }
}