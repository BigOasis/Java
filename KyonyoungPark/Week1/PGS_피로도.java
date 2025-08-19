import java.util.*;

public class PGS {
    class Solution {
        public boolean visited[];
        public int answer = 0;
        public void search(int k, int[][] fatigues, int depth){
            answer = Math.max(answer, depth);
            for(int i = 0; i< fatigues.length; i++){
                if(!visited[i] && k>= fatigues[i][0]){
                    visited[i] = true;
                    search(k-fatigues[i][1], fatigues, depth+1);
                    visited[i] = false;
                }
            }
        }
        public int solution(int k, int[][] dungeons) {
            visited = new boolean[dungeons.length];
            search(k, dungeons, 0);
            return answer;
        }
    }

    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.solution(80, new int[][]{[80,20], [50, 40], [30, 10]}));
    }
}

