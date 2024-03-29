import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] arr, visited;
	static int cnt, T, M, N, K, nr, nc;
	
	// 델타 배열 만들기
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int x, y;
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new boolean[M][N];
			visited = new boolean[M][N];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				arr[x][y] = true;
			}
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] && !visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				if (nr >= 0 && nr < M && nc >= 0 && nc < N) {
					if (arr[nr][nc] && !visited[nr][nc])  {
						queue.add(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
