import java.util.*;

/*
  [스스로 풀이 : 20분] 6ms 47MB
    1. 정렬된 문자열 버전을 키, 원본을 value에 넣기
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();

        for(String str : strs){
          char[] ch = str.toCharArray();
          Arrays.sort(ch);
          String sorted = new String(ch);
          hm.putIfAbsent(sorted,new ArrayList<>());
          hm.get(sorted).add(str);
        }

        List<List<String>> result = new ArrayList<>();
        for(List<String> value : hm.values()){
          result.add(value);
        }

        return result;
    }
}