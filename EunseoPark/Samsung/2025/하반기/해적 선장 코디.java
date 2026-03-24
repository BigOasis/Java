import java.util.*;
import java.io.*;

/*
    1. 각각의 선장이 마지막으로 공격당한 시간이 모두 다름. 
        전역 변수 1개로 lastAttack을 해버리면 중간에 들어온애도 다 동일하게 업데이트됨.
        => 각각 마지막으로 공격 받은 시간 + r <= 현재 시간
    
    2. newPQ에 옮겨 넣을때 넣을때마다 정렬 계산 -> 시간 초과
        더 가벼운 List에 넣어놨다가 마지막에만 넣기
*/
public class Main {

    static class Sheep implements Comparable<Sheep>{
    int id, p, r, lastAttack;

    Sheep(int id, int p, int r, int lastAttack){
        this.id = id; this.p = p; this.r = r; this.lastAttack = lastAttack;
    }

    public boolean canAttack(int t){
        return t >= this.lastAttack + this.r;
    }

    public int compareTo(Sheep o){
        if(this.p == o.p){
            return this.id - o.id;
        }
        return o.p - this.p;
    }

}

    static Map<Integer, Sheep> defaultMap;
    static PriorityQueue<Sheep> pq;

    public static void main(String[] args) throws Exception {
        defaultMap = new HashMap<>();
        pq = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t =1 ; t <= T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            switch(type){
                case 100:{
                    int N = Integer.parseInt(st.nextToken());
                    int id = 0, p = 0, r = 0;
                    for(int n = 0; n < N; n++){
                        id = Integer.parseInt(st.nextToken());
                        p = Integer.parseInt(st.nextToken());
                        r = Integer.parseInt(st.nextToken());
                        setup(id,p,r,-10);
                    }
                    }
                    break;
                case 200:{
                    int id = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    setup(id,p,r,-10);
                    }
                    break;
                case 300:{
                    int id = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    changeSheep(id,p);
                    }
                    break;
                case 400:
                    attack(t);
                    break;
            }
        }    
    }
    static void attack(int t){
        int total = 0; int turn = 0; int count = 0; 
        StringBuilder sb= new StringBuilder();
        List<Sheep> wait = new ArrayList<>(); 

        while(!pq.isEmpty() && count < 5){
            Sheep s = pq.poll();
            if(s.p!= defaultMap.get(s.id).p) continue;

            if(!s.canAttack(t)){
                wait.add(s);
                continue;
            }
            sb.append(" ").append(s.id);
            total += s.p;
            s.lastAttack = t;
            count++;
            // newPQ.offer(s);
            wait.add(s);
        }
        for(Sheep tmp : wait){
            pq.offer(tmp);
        }
        System.out.println(total +" "+count+sb.toString());
    }

    static void changeSheep(int id, int newP){
        Sheep s = defaultMap.get(id);
        Sheep newS = new Sheep(id, newP, s.r, s.lastAttack);
        defaultMap.put(id, newS);
        pq.offer(newS); //우선순위 큐는 이미 들어가 있는 값이 또 들어오면 재정렬되지 않음.
    }

    static void setup(int id, int p, int r,int lastAttack){
        Sheep s = new Sheep(id, p, r, lastAttack);
        defaultMap.put(id, s);
        pq.offer(s);
    }
}
