import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Tomato {
		int r, c, day;
		
		Tomato(int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	static int[][] box;
	static boolean[][] visit;
	static int day, minDay, N, M;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static Queue<Tomato> que = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		visit = new boolean[N][M];
		
		// 익은 토마토의 좌표를 저장할 list
		List<int[]> list = new ArrayList<>();
		
		int n;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				n = Integer.parseInt(st.nextToken());
				box[i][j] = n;
				if (n == 1) list.add(new int[] {i, j});
			}
		}
		
		for (int[] node : list) {
			que.offer(new Tomato(node[0], node[1], 0));
			visit[node[0]][node[1]] = true;
		}
		
		bfs();
		
		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[0].length; j++) {
				if (box[i][j] != -1 && !visit[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(minDay);
	}
	
	static void bfs() {
		while(!que.isEmpty()) {
			Tomato t = que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];
				
				// 접근 불가능한 영역
				if (nr < 0 || nr >= N || nc <0 || nc >= M || 
						visit[nr][nc] || box[nr][nc] == -1) continue;
				
				if (t.day + 1 > minDay) minDay = t.day + 1;
				
				// 현재칸까지 걸린 시간 체크
				visit[nr][nc] = true;
				que.offer(new Tomato(nr, nc, t.day + 1));
				
			}
		}
	}
}
