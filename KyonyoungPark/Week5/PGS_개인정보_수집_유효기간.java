import java.util.*;

// "." 는 안먹는다.. "\\."까먹지말기
class Solution {
    private int calcdays(String date) {
        String[] arr = date.split("\\.");
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);

        return year*12*28 + (month-1)*28 + (day-1);
    }

    private int addmonth(String date, int monthtoadd){
        String[] arr = date.split("\\.");
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);

        month += monthtoadd;

        if(month > 12){
            month -= 12;
            year++;
        }

        return year*12*28 + (month-1)*28 + (day-1);
    }


    public int[] solution(String today, String[] terms, String[] privacies) {

        Map<String, Integer> term = new HashMap<>();
        for(String t: terms) {
            String[] arr = t.split(" ");
            String type = arr[0];
            int month = Integer.parseInt(arr[1]);
            term.put(type, month);
        }

        int todayday = calcdays(today);

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i< privacies.length; i++){
            String[] arr = privacies[i].split(" ");
            String collectdate = arr[0];
            String termtype = arr[1];

            int validmonth = term.get(termtype);
            int expireday = addmonth(collectdate, validmonth);

            if(todayday >= expireday){
                result.add(i+1);
            }
        }



        int[] answer = new int[result.size()];
        for(int i = 0; i<answer.length; i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}