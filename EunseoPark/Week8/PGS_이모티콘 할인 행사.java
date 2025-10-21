import java.util.*;

/*
    [정답 참고]
        완탐이 된다고는 생각했는데 순열처럼 조합을 만들고 나서 사용자 다 돌면서 하는게 생각이 안남
*/
class Solution {
    
    static int[][] Users;
    static int[] Emoticons;
    static int[][] saledPrice;
    static int m, n;
    static int maxPlusCount = 0, maxPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        Users = users;
        Emoticons = emoticons;
        m = emoticons.length;
        n = users.length;
        
        saledPrice = new int[m][4];
        
        for(int i = 0; i< m; i++){
            for(int j = 0; j < 4; j++){
                saledPrice[i][j] = emoticons[i] * (100 - (j + 1) * 10) / 100;
            }
        }
        
        getPrice(0, new int[Emoticons.length]);
        return new int[]{maxPlusCount, maxPrice};
    }
    
    static void getPrice(int index, int[] rates){
        if(index == Emoticons.length){
            calPrice(rates);
            return;
        }
        
        for(int i = 3; i > -1; i--){
            rates[index] = i;
            getPrice(index + 1, rates);
        }
    }
    
    static void calPrice(int[] rates){
        int plusCount = 0;
        int totalPrice = 0;
        
        for(int i = 0; i < n; i++){
            int price = 0;
            
            for(int j = 0; j < m; j++){
                if(Users[i][0] <= (rates[j] + 1)*10){
                    price += saledPrice[j][rates[j]];
                }
            }
            if(price >= Users[i][1]){
                plusCount +=1;
                price = 0;
            }
            totalPrice += price;
        }
        if(maxPlusCount < plusCount){
            maxPlusCount = plusCount;
            maxPrice = totalPrice;
        }
        else if(maxPlusCount == plusCount && maxPrice < totalPrice){
            maxPrice = totalPrice;
        }
        return;
    }
}