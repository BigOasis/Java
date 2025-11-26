import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {

        for(String[] plan : plans){
            String[] time = plan[1].split(":");
            int h = Integer.parseInt(time[0]);
            int m = Integer.parseInt(time[1]);
            
            plan[1] = String.valueOf(h*60 + m);
        }
        
        Arrays.sort(plans, Comparator.comparingInt(plan -> Integer.parseInt(plan[1])));
        Deque<String[]> stack = new ArrayDeque<>();
        List<String> answerList = new ArrayList<>();
        
        for(int i = 0; i < plans.length-1; i++){
            String subject = plans[i][0];
            int startTime = Integer.parseInt(plans[i][1]);
            int duration = Integer.parseInt(plans[i][2]);
            
            int nextStart = Integer.parseInt(plans[i+1][1]);
            
            if(startTime + duration <= nextStart){
                answerList.add(subject);
                
                // 과제하고 나서 남는 시간
                int remainTime = nextStart - (startTime + duration);
                while(remainTime > 0 && !stack.isEmpty()){
                    String[] paused = stack.removeLast();
                    String pausedSubject = paused[0];
                    int pausedDuration = Integer.parseInt(paused[1]);
                    
                    if(remainTime >= pausedDuration){
                        answerList.add(pausedSubject);
                        remainTime -= pausedDuration;
                    }else{
                        stack.addLast(new String[]{pausedSubject, String.valueOf(pausedDuration-remainTime)});
                        break;
                    }
                }
            }else{
                stack.addLast(new String[]{subject, String.valueOf(duration-(nextStart - startTime))});
            }
        }
        // 마지막 과목
        stack.addLast(new String[]{plans[plans.length-1][0], plans[plans.length-1][2]});
        
        while(!stack.isEmpty()){
            answerList.add(stack.removeLast()[0]);
        }
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}