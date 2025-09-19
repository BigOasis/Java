package algo;

import java.io.*;
import java.util.*;

/*
 *	[스스로 풀이 : 40분] 103104kb, 380ms 
 * 	 벽이 3개로 고정이길래 범위보고 그냥 for문 3개로 진행
 */
public class Main5 {

	static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
	static int N,M, result;
	static List<int[]> viruses;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		viruses = new ArrayList<>();
		result = 0;
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int pos = Integer.parseInt(st.nextToken());
				
				if(pos == 2) {
					viruses.add(new int[] {i,j});
				}
				map[i][j] = pos;
			}
		}
		
		int size = N*M;
		for (int i = 0; i < size; i++) {
			int iy = i / M, ix = i % M;
			if(map[iy][ix] != 0) continue;
			
			for (int j = i+1; j < size; j++) {
				int jy = j / M, jx = j % M;
				if(map[jy][jx] != 0) continue;
				
				for (int k = j+1; k < size; k++) {
					int ky = k / M, kx = k % M;
					if(map[ky][kx] != 0) continue;
					
					// 1. 벽 세우기
					buildWall(iy,ix, jy,jx,ky,kx,map);
				}
			}	
		}
		System.out.println(result);
	}
	static void buildWall(int iy, int ix, int jy, int jx, int ky, int kx, int[][] map) {
		//1. 복사
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i]= Arrays.copyOf(map[i], M);
		}
		
		copy[iy][ix] = 1;
		copy[jy][jx] = 1;
		copy[ky][kx] = 1;
		
		//2. 바이러스 퍼트리기
		Deque<int[]> q = new ArrayDeque<>();
		for(int[] v : viruses) {
			q.add(v);
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dy[d], nx = cur[1] + dx[d];
				
				if(inRange(ny,nx) && copy[ny][nx] == 0) {
					copy[ny][nx] = 2;
					q.add(new int[] {ny,nx});
				}
			}
		}


		// 3. safeZone 0의 개수 세기
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0) safeZone++;
			}
		}
		
		result = Math.max(result, safeZone);
		
	}
	static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x <M;
	}

}
