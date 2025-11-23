
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_sec = calSec(video_len);
        int pos_sec = calSec(pos);
        int op_start_sec = calSec(op_start);
        int op_end_sec = calSec(op_end);
        
        for(String command: commands){
            if(op_start_sec <= pos_sec && pos_sec <= op_end_sec){
                pos_sec = op_end_sec;
            }
            if(command.equals("next")){
                pos_sec += 10;
                pos_sec = Math.min(video_len_sec,pos_sec);
                
            }else{
                pos_sec -= 10;
                pos_sec = Math.max(0, pos_sec);
            }
            if(op_start_sec <= pos_sec && pos_sec <= op_end_sec){
                pos_sec = op_end_sec;
            }
        }
        
        return secTime(pos_sec);
    }
    static String secTime(int time){
        String M = String.format("%02d", time/60);
        String S = String.format("%02d", time%60);
        
        return M+":"+S;
    }
    
    static int calSec(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}