import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int curhealth = health;
        int task = 0;
        int success = 0;
        
        for(int i = 1; i <= 1000; i++){
            if(task >= attacks.length) break;
            
            if(i != attacks[task][0]){
                success++;
                curhealth += bandage[1];
                if(success == bandage[0]){
                    success = 0;
                    curhealth += bandage[2];
                }
                curhealth = Math.min(curhealth, health);
            }
            else{
                success = 0;
                curhealth -= attacks[task++][1];
                if(curhealth <= 0) return -1;
            }
            
        }
        return curhealth;
    }
}