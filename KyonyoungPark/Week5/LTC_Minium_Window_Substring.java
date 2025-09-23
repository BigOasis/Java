import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }

        Map<Character,Integer> map_t = new HashMap<>();
        Map<Character,Integer> map_s = new HashMap<>();

        int left = 0, right = 0, valid=0;

        for(char c: t.toCharArray()){
            map_t.put(c,map_t.getOrDefault(c,0)+1);
        }

        int start = 0, min_len= Integer.MAX_VALUE;

        // s에서 탐색시작
        while(right < s.length()){
            char c = s.charAt(right);

            if(map_t.containsKey(c)) {
                map_s.put(c, map_s.getOrDefault(c, 0) + 1);
                if (map_s.get(c).equals(map_t.get(c))) {
                    valid++;
                }
            }
            right++;

            while(valid == map_t.size()){
                //유효할 때마다 최소길이인 지 확인 후 업데이트
                if(right - left < min_len) {
                    start = left;
                    min_len = right - left;
                }

                char d =  s.charAt(left);
                if(map_t.containsKey(d)){
                    if(map_s.get(d).equals(map_t.get(d))){
                        valid--;
                    }
                    map_s.put(d, map_s.get(d) - 1);
                }

                left++;
            }
        }

        return min_len == Integer.MAX_VALUE ? "" : s.substring(start, start + min_len);
    }
}