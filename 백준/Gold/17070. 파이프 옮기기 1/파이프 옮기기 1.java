import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int R = 0;
	static final int U = 1;
	static final int D = 2;
	
	// 좌, 하, 대각선 델타 배열 생성
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 0, 1};
	static int[][] arr;
	static boolean[][] wall;
	
	static int N, nr, nc, cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		wall = new boolean[N][N];
		
		for (int i = 0; i < wall.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < wall[0].length; j++) {
				str = st.nextToken();
				if (str.equals("1")) {
					wall[i][j] = true;
				}
			}
		}

		dfs(0, 1, R);
		
		System.out.println(cnt);
	}
	
	static void dfs(int r, int c, int d) {
		if (r == N - 1 && c == N - 1) {
            cnt++;
            return;
        }
		
		if (d == R) {
			nr = r;
			nc = c + 1;			
			// nr, nc가 배열 범위를 벗어나면 return
			if (nc < N && !wall[nr][nc]) {
				dfs(nr, nc, R);
			}
		} else if (d  == U) {
			
			nr = r + 1;
			nc = c;
			
			// nr, nc가 배열 범위를 벗어나면 return
			if (nr < N && !wall[nr][nc]) {
				dfs(nr, nc, U);
			}
		} else if (d == D) {
			nr = r + 1;
			if (nr < N && !wall[nr][c]) {
				dfs(nr, c, U);
			}
			
			nc = c + 1;
			if (nc < N && !wall[r][nc]) {
				dfs(r, nc, R);
			}
		}
		
		if (r + 1 < N && c + 1 < N && !wall[r + 1][c] && !wall[r][c + 1] && !wall[r + 1][c + 1]) {
			dfs(r + 1, c + 1, D);
		}
	}
}
