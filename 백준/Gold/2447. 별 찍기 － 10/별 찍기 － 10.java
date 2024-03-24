import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		arr = new boolean[N][N];
		
		div(0, 0, N / 3);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j]) sb.append(" ");
				else sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void div(int r, int c, int l) {
		if (l < 1) {
			return;
		}
		
		for (int i = r; i <= r + 2*l; i+= l) {
			for (int j = c; j <= c + 2*l; j += l) {
				if (i == r + l && j == c + l) {
					star(i, j, l);
				} else {
					div (i, j, l / 3);
				}
			}
		}
	}
	
	public static void star(int r, int c, int l) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				arr[i][j] = true;
			}
		}
	}
}
