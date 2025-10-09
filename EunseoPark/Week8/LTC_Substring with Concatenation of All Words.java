import java.util.*;

/*
  [스스로 풀이 : 40분] 2301ms 46MB
    [1차 접근] 
      len = words의 길이
      substring(i, i+len) 을 key, i를 index로 해서 HashMap<String, List<Integer>> 
      permutation으로 words를 조합하려고 했는데 5000개 -> permutation 무조건 시간 초과
      
    [2차 접근] 2301ms 46MB
      words에서 Map<String, Integer>로 각 단어의 개수 
      s에서 (i, i + words 전체의 길이) substring을 만들고, 단어끼리 다시 쪼개서, hm에 넣기
      wordsHm과 비교해서 단어가 없거나, value 개수 안 맞으면 return false
*/
class Solution {
    static String s;
    static String[] words;
    static int wordlen, wordtotalLen;
    static Map<String, Integer> wordHm;

    public List<Integer> findSubstring(String S, String[] Words) {
        s = S; words = Words;

        wordHm = new HashMap<>();
        wordlen = words[0].length();
        wordtotalLen = wordlen * words.length;

        List<Integer> ans = new ArrayList<>();
        if(wordtotalLen > s.length()) return ans;

        for(String word : words){
          wordHm.put(word, wordHm.getOrDefault(word, 0) + 1);
        }

        for(int i = 0, size = s.length(); i <= size - wordtotalLen; i++){
          String sub = s.substring(i, i + wordtotalLen);

          if(check(sub)){
            ans.add(i);
          }
        }
        return ans;
    }

    static boolean check(String sub){
      Map<String, Integer> subHm = new HashMap<>();

      for(int j = 0; j < wordtotalLen; j+= wordlen){
        String subword = sub.substring(j, j + wordlen);
        subHm.put(subword, subHm.getOrDefault(subword, 0) + 1);
      }

      for(String key : wordHm.keySet()){

        if(!subHm.containsKey(key)) {
          return false;
        }

        if(subHm.get(key).intValue() != wordHm.get(key).intValue()){
          return false;
        } 
      }
      return true;
    }
}
