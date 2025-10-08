import java.util.*;

//1차 전략 -> 매번 누적 주차요금 String 해석하면서 해시맵 value 교체했는데
// 어차피 누적된다고 해서 전략 변경
class Solution {
    // 시간을 분으로 변환
    private int calcTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    private int calcPayment(int totalTime, int[] fees) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int additionalTime = fees[2];
        int additionalFee = fees[3];

        if (totalTime <= baseTime) {
            return baseFee;
        }

        int extraTime = totalTime - baseTime;
        //올림할 때 실수함 주의
        int extraUnits = (int) Math.ceil((double) extraTime / additionalTime);

        return baseFee + (extraUnits * additionalFee);
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> parking = new HashMap<>();

        // 각 차량의 총 주차 시간으로 관리
        Map<String, Integer> totalTime = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                parking.put(carNumber, calcTime(time));
            } else {
                int inTime = parking.get(carNumber);
                int outTime = calcTime(time);
                int parkTime = outTime - inTime;

                totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + parkTime);
                parking.remove(carNumber);
            }
        }

        int endOfDay = calcTime("23:59");
        for (String carNumber : parking.keySet()) {
            int inTime = parking.get(carNumber);
            int parkTime = endOfDay - inTime;
            totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + parkTime);
        }

        List<String> carNumbers = new ArrayList<>(totalTime.keySet());
        Collections.sort(carNumbers);

        int[] answer = new int[carNumbers.size()];
        for (int i = 0; i < carNumbers.size(); i++) {
            String carNumber = carNumbers.get(i);
            int time = totalTime.get(carNumber);
            answer[i] = calcPayment(time, fees);
        }

        return answer;
    }
}