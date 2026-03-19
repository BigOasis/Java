import java.util.*;
import java.io.*;

/**
    나갈 수 있는 지 탐색, 모든 위치 탐색, 중복 탐색 블록 존재 : O(N^3)
        for(int i = 1; i <= N; i++){
            for(int j = start; j != end + step; j += step){
                int num = graph[i][j];
                ...
                if(canGetOut(...)){   // ← 여기

    블록 단위 탐색 : O(N^2)
*/
public class Main {
    static int N,M;
    static int[][] graph;
    static Map<Integer,Rect> map = new HashMap<>();

    static class Rect{
        int y,x,h,w;

        public Rect(int Y,int X, int H, int W){
            this.y = Y; this.x = X; this.h = H; this.w = W;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        for(int t =0; t < M; t++){
            st = new StringTokenizer(br.readLine());
            int Num = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = 1;

            // 1. 넣기
            inputGraph(Num, H, W, X, Y);
            map.put(Num,new Rect(Y,X,H,W));

            Down(Num, H, W, X, Y);
        }

        int count = 0;
        while(count < M){
            Set<Integer> set = new HashSet<>();
            // 왼
            int dir = (count % 2 == 0) ? 1 : 2;
            int start = dir == 1 ? 1 : N ;
            int end = dir == 1 ? N : 1;
            int step = dir == 1 ? 1: -1;

            // 위에서부터 한줄한줄 뺄수있는지가 아니라 블록 단위로 1뺄수있나? 2뺄수있나? 하면 중복 탐색 블록은 안 생김
            for(int num : map.keySet()){
                Rect r = map.get(num);
                if(canGetOut(dir == 1 ? 1 : 2, r.y, r.x, r.h, r.w)){
                    set.add(num);
                }
            }

            int min = 101;
            for(int num : set){
                min = Math.min(min, num);
            }

            // 빼기
            Rect r = map.get(min);

            reset(r.x, r.y, r.h, r.w);
            map.remove(min);
            System.out.println(min);
            
            // 내리기
            for(int i = r.y + r.h -1 ; i > 0; i--){
                for(int j = 1; j <= N; j++){
                    if(graph[i][j] == 0) continue;

                    Rect r1 = map.get(graph[i][j]);
                    Down(graph[i][j], r1.h, r1.w, r1.x, r1.y);
                }
            }
            count++;

        }
    }
    static void print(){
        for(int i = 1; i <=N; i++){
            System.out.println(Arrays.toString(graph[i]));
        }
        System.out.println();
    }

    static boolean canGetOut(int dir, int Y, int X, int H, int W){
        int start = dir == 1 ? 1 : X + W;
        int end = dir == 1 ? X : N + 1;

        for(int h = 0; h < H; h++){
            for(int x = start; x < end; x++){
                if(graph[Y+h][x] != 0) return false;
            }
        }
        return true;
    }

    static void Down(int Num, int H, int W, int X, int Y){
        //1. 밑으로 내리기
        int canDown = 0;
        loop: while(canDown < N){
            for(int w = 0; w< W; w++){
                if(isOut(Y+ H + canDown) || graph[Y + H + canDown][X+w] != 0) break loop;
            }
            canDown++;
        }
        if(canDown > 0){
            // 초기화
            reset(X, Y, H, W);
            Y += canDown;

            // 이동해서 넣기
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    graph[Y+i][X+j] = Num;
                }
            }
            inputGraph(Num, H, W, X, Y);
            map.put(Num,new Rect(Y,X,H,W));
        }
    }

    static void inputGraph(int Num, int H, int W, int X, int Y){
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                graph[Y+i][X+j] = Num;
            }
        }
    }

    static void reset(int X, int Y, int H, int W){
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                graph[Y+i][X+j] = 0;
            }
        }
    }

    static boolean isOut(int y){
        return y > N;
    }

}
