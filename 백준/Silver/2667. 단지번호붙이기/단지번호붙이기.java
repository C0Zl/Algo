import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int cnt;
	public static int N;
	public static boolean[][] visited;
	public static int[][] arr;
	
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
	
	StringBuilder sb = new StringBuilder();

	
	public static List<Integer> cmpl = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] st = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st[j]);
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] == 1 && !visited[r][c]) {
					cnt = 0;
					cmpl.add(dfs(r, c));
				}
			}
		}
		
		Collections.sort(cmpl);
		System.out.println(cmpl.size());
		for (int k : cmpl) {
			System.out.println(k);
		}
		
	}
	
	public static int dfs(int r, int c) {
		cnt++;
		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (arr[nr][nc] == 1 && !visited[nr][nc]) {
					dfs(nr, nc);
				}
			}
		}
		return cnt;
	}

}
