import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] arr;
	static int N, M, r, c, dir, cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		// 상, 좌, 하, 우 순으로 다시 정렬
		if (d == 3) dir = 1;
		else if (d == 1) dir = 3;
		else dir = d;
		
		arr = new int[N][M];
		
		for (int r = 0; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < arr[0].length; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(r, c, dir);
		System.out.println(cnt);
	}
	
	static void clean(int r, int c, int dir) {
		if (arr[r][c] == 0) {
			// 청소가 되지 않았다면 ?
			arr[r][c] = -1;
			cnt++;
//			System.out.println("[" + cnt + "] r : " + r + ", c : " + c + ", d : " + dir);
		}
		
		for (int d = dir + 1; d <= dir + 4; d++) {
			int nr = r + dr[d % 4];
			int nc = c + dc[d % 4];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == 1) continue;
			if (arr[nr][nc] == 0) {
				clean(nr, nc, d);
				// break;는 왜 안됨
				return;
			}
		}
		int d = (dir + 2) % 4;
		int nr = r + dr[d];
		int nc = c + dc[d];
		if(arr[nr][nc] == 1) {
			return;
		} else {
			
			clean(nr, nc, dir);
		}
		
	}
	
}
