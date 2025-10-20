import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*	
 * 	27412kb, 252ms
 *	[오답 틀림]
 *		[처음 접근 : 시간초과]
 *			0. 승객 위치 입력받을 때 출발 ~ 거리는 구해놓기
 *			1. 택시가 이동할 때마다 택시 위치 ~ 남은 승객 거리를 모두 계산 후 그때의 최단 거리 승객으로 이동
 *			2. 이러면 매번 M번 마다 BFS 탐색으로  
 *				N X N : 전체 돌기
 *				M명의 남은 승객들로 이동하는 거리
 *				M명을 모두 택배로 이동시킬 때까지 반복
 *			M * M * (N * N) * logN (우선 순위 큐로 BFS)	=> 5.5 X 10^8 으로 되는 것처럼 보이지만
 *													상수 오버헤드(객체 생성, 힙 연산, 분기 등) -> 자바 1~2초 제한에서 시간초과
 *		[2차 시도]
 *			0. 아예 현재 택시 ~ 모든 위치 다 돌면서 도달 거리 계산하고,
 *			1. 남은 승객 위치 돌면서 거리 더 짧고, 행 작고, 열 작으면 다음 위치로 갱신해서 1번만 BFS 거리 계산하기
 */
public class Main {
	
	static int N;
	static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
	static int[][] map;
	
	static class Customer implements Comparable<Customer>{
		int index, sy, sx, ey,ex, sw; // sw : 승객 sy,sx ~ 승객 ey, ex
		
		public Customer(int sy, int sx, int ey, int ex, int sw) {
			this.sy = sy; this.sx = sx; this.ey = ey; this.ex = ex; this.sw = sw; 
		}
		
		public void setIndex(int index) {
			this.index = index;
		}

		@Override
		public int compareTo(Customer o) {
			if(this.sy == o.sy) {
				return this.sx - o.sx; //3번 : 시작 열이 작은 순
			}
			return this.sy - o.sy; //2번 : 시작 행이 작은 순
		}
	
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int tw = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int ty = Integer.parseInt(st.nextToken()) - 1;
		int tx = Integer.parseInt(st.nextToken()) - 1;

		PriorityQueue<Customer> customers = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			
			int distanceFromTo = getDistance(sy, sx, ey,ex);
			if(distanceFromTo < 0) {
				System.out.println(-1);
				return;
			}
			customers.add(new Customer(sy, sx, ey, ex, distanceFromTo));
		}

		while(M > 0) {
			int[][] dist = bfsTexiToCustomers(ty, tx);
			Customer pick = null;
			int nextTy = - 1, nextTx = -1;
			int D = N*N;
			
			for(Customer c : customers) {
				 int d = dist[c.sy][c.sx];
				    if (d == -1) continue; // 도달 불가 스킵

				if(d < D || 
						(d == D && (nextTy > c.sy || 
						(c.sy == nextTy && nextTx > c.sx)))) {
					pick = c;
					nextTy = c.sy; nextTx = c.sx;
					D = dist[c.sy][c.sx];	
				}
			}
			if(pick == null) {
				System.out.println(-1);
				return;
			}
			
			tw = tryDrive(tw, D, pick.sw);
			if(tw == -1) {
				System.out.println(-1);
				return;
			}
			ty = pick.ey; tx = pick.ex;
			customers.remove(pick);
			M--;
			
		}
		
		System.out.println(tw);
	}
	static int tryDrive(int tw, int dis, int sw) {
		tw = tw - dis - sw;
		if(tw < 0) return -1;
		
		tw += sw + sw;
		return tw;
	}
	
	// 택시 ~ 모든 승객 거리 계산
	static int[][] bfsTexiToCustomers(int sy, int sx){
		int[][] dist = new int[N][N];
		for(int[] row : dist) Arrays.fill(row, -1);
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sy,sx, 0});
		dist[sy][sx] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1], w = cur[2];
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d], nx = x + dx[d];
				
				if(inRange(ny,nx) && map[ny][nx] == 0 && dist[ny][nx] == -1) {
					dist[ny][nx] = w + 1;
					q.add(new int[] {ny,nx, w+1});
				}
			}
		}
		return dist;
	}
	
	// 승객 출발 -> 도착 거리 계산
	static int getDistance(int sy, int sx, int ey, int ex) {
	    if (sy == ey && sx == ex) return 0;
	    ArrayDeque<int[]> q = new ArrayDeque<>();
	    boolean[][] v = new boolean[N][N];
	    q.add(new int[]{sy, sx, 0});
	    v[sy][sx] = true;
		
	    while (!q.isEmpty()) {
	        int[] cur = q.poll();
	        int y = cur[0], x = cur[1], w = cur[2];
	        
	        for (int d = 0; d < 4; d++) {
	            int ny = y + dy[d], nx = x + dx[d];
	        
	            if (inRange(ny, nx) && map[ny][nx] != 1 && !v[ny][nx]) {
	                if (ny == ey && nx == ex) return w + 1;
	                v[ny][nx] = true;
	                q.add(new int[]{ny, nx, w + 1});
	            }
	        }
	    }
	    return -1;
	}
	
	static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x < N;
	}

}
