
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = calSec(play_time);
        long[] dp = new long[playSec + 1];
        
        for(String log : logs){
            String[] data = log.split("-");
            String start = data[0], end = data[1];
            dp[calSec(start)] += 1;
            dp[calSec(end)] += -1;
        }
        
        // dp[i] 시간대에 재생 중인 광고 개수 세기
        for(int i = 1; i <= playSec; i++){
            dp[i] = dp[i] + dp[i-1];
        }
        
        // 누적 합
        for(int i = 1; i <= playSec; i++){
            dp[i] = dp[i] + dp[i-1];
        }
        
        int advSec = calSec(adv_time);
        
        long maxCount = dp[advSec];
        int maxStartTime = 0;
        
        for(int i = 1, until = playSec - advSec; i <= until; i++){
            long start = dp[i - 1];
            long end = dp[i + advSec - 1];
            long count = end - start;
            
            if(maxCount < count){
                maxStartTime = i;
                maxCount = count;
            }
        }
        
        return secToString(maxStartTime);
    }
    static String secToString(int time){
        int H = time / 3600;
        time %= 3600;
        
        int M = time / 60;
        time %= 60;
        
        String[] data = {String.format("%02d", H), String.format("%02d", M), String.format("%02d", time)};
        return String.join(":", data);
    }
    
    static int calSec(String time){
        String[] data = time.split(":");
        int H = 3600 * Integer.parseInt(data[0]);
        int M = 60 * Integer.parseInt(data[1]);
        int S = Integer.parseInt(data[2]);
        
        return H + M + S;
    }
}