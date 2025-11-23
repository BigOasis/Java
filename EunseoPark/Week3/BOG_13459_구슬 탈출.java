package algo;

import java.io.*;
import java.util.*;

/*
 *	[스스로 풀이] 
 *		1. 중복 방문을 안하면 전체 100개의 좌표에서 방향 4가지니까 100^4가 되므로 시간초과 난다고 생각
 *		2. visited로 중복 방문을 제거 -> List.of 방법도 있지만 아예 다 더한 string으로 해도 됨
 *		3. 63%에서 첫 시도 틀렸다고 나옴 -> 처음 이동부터 time 이 0이 아니라 1로 해야 함 (이 부분은 질문 게시판 보고 알았다. 문제에서 찾기 어려움)
*/
public class Main7 {
	
	static int N,M;
	static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};
	static Set<List<Integer>> visited = new HashSet<>();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int Ry = 0, Rx = 0, By = 0, Bx = 0;
		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int  j= 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				if(board[i][j] == 'R') {
					Ry = i; Rx = j;
					board[i][j] = '.';
				}
				if(board[i][j] == 'B') {
					By = i; Bx = j;
					board[i][j] = '.';
				}
			}
		}
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {Ry, Rx, By, Bx, 1});
		visited.add(List.of(Ry,Rx,By,Bx));
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			Ry = cur[0]; Rx = cur[1]; By = cur[2]; Bx = cur[3]; int time = cur[4];

			for (int d = 0; d < 4; d++) {
				int Rny = Ry + dy[d], Rnx = Rx + dx[d];		
				int Bny = By + dy[d], Bnx = Bx + dx[d];
				
				boolean Rflag = false; int Rmove = 0;
				while(board[Rny][Rnx] != '#') {
					if(board[Rny][Rnx] == 'O') {
						Rflag = true;
						break;
					}
					Rmove++;
					Rny += dy[d]; Rnx += dx[d];
				}
				if(!Rflag) {
					Rny -= dy[d]; Rnx -= dx[d];
				}
				
				boolean Bflag = false; int Bmove = 0;
				while(board[Bny][Bnx] != '#') {
					if(board[Bny][Bnx] == 'O') {
						Bflag = true;
						break;
					}
					Bmove++;
					Bny += dy[d]; Bnx += dx[d];
				}
				if(!Bflag) {
					Bny -= dy[d]; Bnx -= dx[d];
				}
				
				if(Bflag) continue;
				if(!Bflag && Rflag) {
					System.out.println(1);
					return;
				}
				if(Rny == Bny && Rnx == Bnx) {
					if(Rmove > Bmove) {
						Rny -= dy[d]; Rnx -= dx[d];
					}else {
						Bny -= dy[d]; Bnx -= dx[d];
					}
				}
				if(time <10 && !visited.contains(List.of(Rny,Rnx,Bny,Bnx))) {
					q.add(new int[] {Rny,Rnx,Bny,Bnx,time+1});
					visited.add(List.of(Rny,Rnx,Bny,Bnx));
				}
			}
			
		}
		System.out.println(0);
		
    }
}
