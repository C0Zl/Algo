import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static class Node {
		int r, c, len, brokenNum;
		
		Node(int r, int c, int len, int brokenNum) {
			this.r = r;
			this.c = c;
			this.len = len;
			this.brokenNum = brokenNum;
		}
	}

	static boolean[][] map;
	static int[][] visit;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int N, M, minLen = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n과 m 입력 받기
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new boolean[N][M];
		visit = new int[N][M];
		
		// map 배열 완성하기 (1은 true, 0은 false)
		for (int r = 0; r < map.length; r++) {
			String[] line = br.readLine().split("");
			for (int c = 0; c < map[0].length; c++) {
				if (line[c].equals("1"))
					map[r][c] = true;
				visit[r][c] = Integer.MAX_VALUE;
			}
		}
		
		// dfs 시작
		visit[0][0] = 0;
		bfs(0, 0);
		System.out.println(minLen);
	}

	// 현재 좌표와 이동한 거리, 칸 부쉈는지 여부
	static void bfs(int r, int c) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(r, c, 1, 0));
		
		while(!que.isEmpty()) {
			Node curr = que.poll();
			
			if (curr.r == N - 1 && curr.c == M - 1) {
				minLen = curr.len;
				break;
			}
			
			for (int d = 0; d < dr.length; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				// map 배열의 범위를 벗어나는지 확인
				if (nr <0 || nr >= N || nc <0 ||nc >= M || visit[nr][nc] <= curr.brokenNum) continue;
				
				// 1. 1을 만나는 경우
				if (map[nr][nc]) {
					// 1-(1) 이미 벽을 부쉈다면, 이길로는 못 감
					if(curr.brokenNum != 0) continue;
					
					// 1-(2) 벽을 부수지 않았다면, 벽을 부술지 말지 결정
					// * 벽을 부순다.
					que.add(new Node(nr, nc, curr.len + 1, curr.brokenNum+1));
					visit[nr][nc] = curr.brokenNum +1;
				}
					
				if (!map[nr][nc]) {
					// 2. 0을 만나는 경우
					que.add(new Node(nr, nc, curr.len + 1, curr.brokenNum));
					visit[nr][nc] = curr.brokenNum;				
				}
			}
		}
		
	}
}
