// 전형적인 슬라이딩 윈도우 문제(X)
// (동적 크기 윈도우면 슬라이딩 윈도라고 안한다네요.. ㅈㅅ) 투 포인터 문제

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int types = gemSet.size();

        // 현재 윈도우에 포함된 보석 개수 추적
        Map<String, Integer> cur = new HashMap<>();

        int left = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < gems.length; right++) {
            String gem = gems[right];
            cur.put(gem, cur.getOrDefault(gem, 0) + 1);

            while (cur.size() == types) {
                int curLength = right - left + 1;

                if (curLength < min) {
                    min = curLength;
                    start = left;
                }

                String leftGem = gems[left];
                cur.put(leftGem, cur.get(leftGem) - 1);

                if (cur.get(leftGem) == 0) {
                    cur.remove(leftGem);
                }

                left++;
            }
        }

        return new int[]{start + 1, start + min};
    }
}