import java.util.*;

/*
    문자열.substring(startIndex, endIndex)
    
    [ 정답 참고 ] 20ms
	    1. 첫 시도는 a, aa , aab 이렇게 만들어서 새로운 문자열 자체를 계속
		    넘겨야 하나 해서 헷갈리고 꼬임
		    
		 // 정답 참고 정리
			 1. 문자열이 아닌 (start index) 를 넘겨 새로운 문자열을 만들기
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        part(result, new ArrayList<>(), s, 0);
        return result;        
    }

    private void part(List<List<String>> result, List<String> cur, String origin, int start){
        if(start == origin.length()) {
            result.add(new ArrayList<>(cur));
            return;
        }
        
        for(int i = start + 1, length = origin.length(); i <= length; i++){
            String newStr = origin.substring(start, i);

            if(isSame(newStr)){
                cur.add(newStr);
                part(result, cur, origin, i);
                cur.remove(cur.size()-1);
            }
        }
    }
    private boolean isSame(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString().equals(s);
    }
}