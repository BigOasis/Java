class Solution {
    private int calcTime(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));
        int second = Integer.parseInt(time.substring(6,8));
        return 3600*hour + minute*60 + second;
    }

    private String toTime(int time){
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int second = time % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = calcTime(play_time);
        int advTime = calcTime(adv_time);

        if (playTime == advTime) return "00:00:00";

        int[] viewers = new int[playTime + 1];
        for(String log : logs){
            String[] times = log.split("-");
            int start = calcTime(times[0]);
            int end = calcTime(times[1]);
            viewers[start] += 1;
            viewers[end] -= 1;
        }

        for(int i = 1; i <= playTime; i++){
            viewers[i] += viewers[i-1];
        }

        // viewers를 바탕으로 누적 재생시간 계산
        // 300000 * (360000-1) 이면 int 오버플로나니까 롱쓰기
        long[] totalView = new long[playTime + 1];
        for(int i = 1; i <= playTime; i++){
            totalView[i] = totalView[i-1] + viewers[i];
        }

        // 0 ~ advTime 부터 endTime-advTime ~ endTime 계산하기
        long maxView = totalView[advTime+1];
        int answerTime = 0;

        for(int start = 1; start + advTime+1 <= playTime; start++){
            long currentView = totalView[start + advTime+1] - totalView[start];
            if(currentView > maxView){
                maxView = currentView;
                answerTime = start;
            }
        }

        return toTime(answerTime);
    }
}