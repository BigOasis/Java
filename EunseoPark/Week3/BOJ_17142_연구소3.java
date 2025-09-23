import java.io.*;
import java.util.*;

/*
 *	[스스로 풀이 : 2h] 39340kb, 268ms
 *		originMap 을 copy해서 방문한 곳은 -1로 채워서 copy 맵 하나로 해결하려고 했는데, 
 *		바이러스가 있는 곳도 퍼트리면서 이동할 수 있어야 함. 즉 바이러스를 -1(벽)으로 해버리면 막혀서 못감 -> 반례 발생
 *
 *  	그냥 visited 맵을 두어서 방문 처리로 중복 방문 방지
 *  	goCount == road가 같아지는 시점에 끝낼지 말지 결정해야 하는 부분 찾는게 오래 걸렸음. 초반에는 while 문에서 cur을 뽑았을 때 하려고 했는데
 *  	실제 정답보다 +1이거나 -1이 됨. 
 */
public class Main {

	static int N,M, result, road;
	static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1};	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;
		
		int[][] originMap = new int[N][N];
		List<int[]> viruses = new ArrayList<>(); //바이러스 위치
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int pos = Integer.parseInt(st.nextToken());
				
				// 바이러스
				if(pos == 2) {
					viruses.add(new int[] {i,j});
					originMap[i][j] = pos;
				}
				// 벽
				else if(pos == 1){
					originMap[i][j] = -1; 
				}
				// 길
				else {
					originMap[i][j] = pos;
					road ++;
				}
			}
		}
		
		if(road == 0) {
			System.out.println(0);
			return;
		}
		// 1. 조합 뽑기
		getVirusCombination(viruses, originMap);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(result);

	}
	
	static void getVirusCombination(List<int[]> viruses, int[][] originMap) {
		makeCombination(viruses, originMap,new ArrayList<>(), 0);
	}
	
	static void makeCombination(List<int[]> viruses, int[][] originMap, List<int[]> cur, int start) {
		if(cur.size() == M) {
			//바이러스 퍼트리기
			spreadVirus(cur, originMap);
			return;
		}
		
		for (int i = start, size = viruses.size(); i < size; i++) {
			cur.add(viruses.get(i));
			makeCombination(viruses, originMap, cur, i+1);
			cur.remove(cur.size()-1);
		}
	}
	
	static boolean spreadVirus(List<int[]> virus, int[][] originMap) {
		// 1. 맵 복사
		int goCount = 0;
		int max = 0;
		int[][] copy = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(originMap[i], N);
		}
		
		// 2. 퍼트리기 (y,x,count)
		Deque<int[]> q = new ArrayDeque<>();
		for(int[] v : virus) {
			int y = v[0], x = v[1];
			q.add(new int[] {y,x,0});
			visited[y][x] = true;
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1], count = cur[2];
			max = Math.max(max, count);

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d], nx = x + dx[d];
				
				if(inRange(ny,nx) && copy[ny][nx] != - 1 && !visited[ny][nx] ) {
					if(copy[ny][nx] == 0) {
						copy[ny][nx] = count + 1;	
						goCount++;
					}
					
					// 마지막 길 채운 순간 확인
					if(goCount == road) {
						result = Math.min(result, count + 1);
						return true;
					}
					
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx, count+1});
				}
			}
		}
		

		return false;

		
	}
	static boolean inRange(int y, int x) {
		return -1 < x && x <N && -1 < y && y <N;
	}

}
