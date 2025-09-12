import java.util.*;
/*  
    [스스로 풀이 : 30m]
        1. 부분 조합 1개,2개, 3개, 4개,,, 길이까지 뽑고
        2. 바로 유일성 && 최소성 확인
        3. 유일성 : 행 다 돌면서 set에 넣고 set.size()==행 길이면 true
                   조합에서 선택된 값을 문자열 key로 
        4. 최소성 : 새로 들어갈 인덱스가 기존에 들어간 인덱스를 포함하면 false
*/
class Solution {
    static int col, row;
    
    public int solution(String[][] relation) {
        Set<List<Integer>> answer = new HashSet<>();
        row = relation[0].length;
        col = relation.length;
        
        for(int i = 0; i <= row; i++){
            List<List<Integer>> combs = combination(row, i);
            for(List<Integer> comb : combs){
                
                if(isOnly(comb, relation) && isMin(comb, answer)){
                    answer.add(comb);
                }
            }
        }

        return answer.size();
    }
    // 최소성
    private boolean isMin(List<Integer> comb, Set<List<Integer>> answer){
        for(List<Integer> ans : answer){
            if(comb.containsAll(ans)) return false;
        }
        return true;
    }
    
    // 유일성
    private boolean isOnly(List<Integer> comb, String[][] relation){
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < col; i++){
            StringBuilder sb = new StringBuilder();
            for(int com : comb){
                sb.append(relation[i][com]);    
            }
            set.add(sb.toString());
        }
        return set.size() == col;
    }
    
    
    private List<List<Integer>> combination(int n, int r){
        List<List<Integer>> result = new ArrayList<>();
        generateComb(n, r, result, new ArrayList<>(), 0);
        return result;
    }
    
    private void generateComb(int n, int r, List<List<Integer>> result, List<Integer> cur, int start){
        if(cur.size() == r){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i = start; i< n; i++){
            cur.add(i);
            generateComb(n, r, result, cur, i + 1);
            cur.remove(cur.size()-1);
        }
    }
}