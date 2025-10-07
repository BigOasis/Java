import java.util.*;

/*
    [최적화]
        carSumT의 차 번호를 그냥 오름차순 정렬하면서 넣기 위해 TreeMap
*/
class Solution {
    static final int lastT = 1439; //23:59
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> car = new HashMap<>();
        Map<String, Integer> carSumT = new TreeMap<>(); // 차 번호 오름차순 정렬
        
        for(String record : records){
            String[] data = record.split(" ");
            String carNum = data[1];
            
            String[] time = data[0].split(":");
            int H = Integer.parseInt(time[0]), M = Integer.parseInt(time[1]);
            int translateT = H * 60 + M;
            
            //입차
            if(!car.containsKey(carNum)){
                car.put(carNum, translateT);
            }else{
                int stayT = translateT - car.get(carNum);
                carSumT.put(carNum, carSumT.getOrDefault(carNum, 0) + stayT);
                car.remove(carNum); //출차
            }
         }
        for(String carNum : car.keySet()){
            int stayT = lastT - car.get(carNum);
            carSumT.put(carNum, carSumT.getOrDefault(carNum, 0) + stayT);
        }
        
        int[] answer = new int[carSumT.size()];
        int i = 0;
        for(int t : carSumT.values()){
            answer[i++] = calFee(t,fees[0], fees[1], fees[2], fees[3]);
        }

        return answer;
    }
    
    static int calFee(int time, int defaultT, int defaultFee, int min, int minFee){
        if(time <= defaultT) return defaultFee;
        return defaultFee + (int) Math.ceil(((time - defaultT) / (double)min)) * minFee; 
    }
}

import java.util.*;

/*
    [스스로 풀이 : 30분]
        시간 -> 분으로 환산
        1. 이미 hm에 차 번호가 있다면 출차
            출차 시간 - 입차 시간 변경해서 carSumT (누적 시간)에 getOrDefault로 누적
        2. 없다면 입차
        
        3. 다 돌고 아직 hm에 남아있다면 23:59에 출차 
        4. key값 오름차순 후 getValue로 fee 계산
*/
class Solution {
    static final int lastT = 1439; //23:59
    
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> car = new HashMap<>();
        Map<Integer, Integer> carSumT = new HashMap<>();
        
        for(String record : records){
            String[] data = record.split(" ");
            int carNum = Integer.parseInt(data[1]);
            
            String[] time = data[0].split(":");
            int H = Integer.parseInt(time[0]), M = Integer.parseInt(time[1]);
            int translateT = H * 60 + M;
            
            //입차
            if(!car.containsKey(carNum)){
                car.put(carNum, translateT);
            }else{
                int stayT = translateT - car.get(carNum);
                carSumT.put(carNum, carSumT.getOrDefault(carNum, 0) + stayT);
                car.remove(carNum); //출차
            }
         }
        for(int carNum : car.keySet()){
            int stayT = lastT - car.get(carNum);
            carSumT.put(carNum, carSumT.getOrDefault(carNum, 0) + stayT);
        }
        
        List<Integer> keys = new ArrayList<>();
        for(int key : carSumT.keySet()){
            keys.add(key);
        }
        Collections.sort(keys);
        int[] answer = new int[keys.size()];
        int i = 0;
        for(int key : keys){
            answer[i++] = calFee(carSumT.get(key),fees[0], fees[1], fees[2], fees[3]);
        }

        return answer;
    }
    
    static int calFee(int time, int defaultT, int defaultFee, int min, int minFee){
        if(time <= defaultT) return defaultFee;
        return defaultFee + (int) Math.ceil(((time - defaultT) / (double)min)) * minFee; 
    }
}